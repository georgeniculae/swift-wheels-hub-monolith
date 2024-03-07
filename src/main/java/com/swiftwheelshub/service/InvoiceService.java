package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.InvoiceDto;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.BookingStatus;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.CarStatus;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.entity.Invoice;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.exception.NotFoundException;
import com.swiftwheelshub.exception.SwiftWheelsHubException;
import com.swiftwheelshub.mapper.InvoiceMapper;
import com.swiftwheelshub.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final EmployeeService employeeService;
    private final RevenueService revenueService;
    private final CarService carService;
    private final InvoiceMapper invoiceMapper;

    @Transactional
    public InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDto) {
        Long actualId = getId(id, invoiceDto.getId());

        Invoice existingInvoice = findEntityById(actualId);
        validateInvoice(invoiceDto, existingInvoice.getBooking().getDateFrom());

        Invoice updatedExistingInvoice = updateExistingInvoice(invoiceDto, existingInvoice);
        registerRevenue(updatedExistingInvoice);

        Invoice savedInvoice = invoiceRepository.save(updatedExistingInvoice);

        return invoiceMapper.mapEntityToDto(savedInvoice);
    }

    public List<InvoiceDto> findAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::mapEntityToDto)
                .toList();
    }

    public List<InvoiceDto> findAllActiveInvoices() {
        return invoiceRepository.findAllActiveInvoices()
                .stream()
                .map(invoiceMapper::mapEntityToDto)
                .toList();
    }

    public List<InvoiceDto> findAllInvoicesByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId)
                .stream()
                .map(invoiceMapper::mapEntityToDto)
                .toList();
    }

    public InvoiceDto findInvoiceById(Long id) {
        Invoice invoice = findEntityById(id);

        return invoiceMapper.mapEntityToDto(invoice);
    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public InvoiceDto findInvoiceByComments(String searchString) {
        return invoiceRepository.findInvoiceByComments(searchString)
                .map(invoiceMapper::mapEntityToDto)
                .orElseThrow(() -> new NotFoundException("Invoice with comment: " + searchString + " does not exist"));
    }

    public Long countInvoices() {
        return invoiceRepository.count();
    }

    public long countAllActiveInvoices() {
        return invoiceRepository.countAllActiveInvoices();
    }

    public Invoice findEntityById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " does not exist"));
    }

    private void validateInvoice(InvoiceDto invoiceDto, LocalDate dateFrom) {
        validateDateOfReturnOfTheCar(invoiceDto.getCarDateOfReturn(), dateFrom);

        if (invoiceDto.getIsVehicleDamaged() && ObjectUtils.isEmpty(invoiceDto.getDamageCost())) {
            throw new SwiftWheelsHubException(
                    HttpStatus.BAD_REQUEST,
                    "If the vehicle is damaged, the damage cost cannot be null/empty"
            );
        }
    }

    private Invoice updateExistingInvoice(InvoiceDto invoiceDto, Invoice existingInvoice) {
        Employee receptionistEmployee = employeeService.findEntityById(invoiceDto.getReceptionistEmployeeDetails().getId());

        Car car = carService.findEntityById(existingInvoice.getCar().getId());
        car.setCarStatus(invoiceDto.getIsVehicleDamaged() ? CarStatus.BROKEN : CarStatus.AVAILABLE);

        existingInvoice.setCarDateOfReturn(invoiceDto.getCarDateOfReturn());
        existingInvoice.setReceptionistEmployee(receptionistEmployee);
        existingInvoice.setIsVehicleDamaged(invoiceDto.getIsVehicleDamaged());
        existingInvoice.setDamageCost(invoiceDto.getDamageCost());
        existingInvoice.setAdditionalPayment(invoiceDto.getAdditionalPayment());
        existingInvoice.setComments(invoiceDto.getComments());
        Booking booking = existingInvoice.getBooking();
        booking.setStatus(BookingStatus.CLOSED);
        existingInvoice.setTotalAmount(getTotalAmount(existingInvoice, booking));

        return existingInvoice;
    }

    private void validateDateOfReturnOfTheCar(LocalDate dateOfReturnOfTheCar, LocalDate dateFrom) {
        LocalDate currentDate = LocalDate.now();

        if (dateFrom.isAfter(currentDate) &&
                (currentDate.isBefore(dateOfReturnOfTheCar) || currentDate.equals(dateOfReturnOfTheCar))) {
            throw new SwiftWheelsHubException(
                    HttpStatus.BAD_REQUEST,
                    "The booking is not started yet"
            );
        }

        if (dateOfReturnOfTheCar.isBefore(currentDate)) {
            throw new SwiftWheelsHubException(
                    HttpStatus.BAD_REQUEST,
                    "Date of return of the car cannot be in the past"
            );
        }
    }

    private Long getId(Long id, Long updatedInvoiceId) {
        Long actualId = updatedInvoiceId;

        if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

    private void registerRevenue(Invoice existingInvoice) {
        Revenue revenue = new Revenue();

        revenue.setDateOfRevenue(existingInvoice.getCarDateOfReturn());
        revenue.setAmountFromBooking(existingInvoice.getTotalAmount());

        revenueService.saveEntity(revenue);
    }

    private BigDecimal getTotalAmount(Invoice existingInvoice, Booking booking) {
        LocalDate carReturnDate = existingInvoice.getCarDateOfReturn();
        LocalDate bookingDateTo = booking.getDateTo();
        LocalDate bookingDateFrom = booking.getDateFrom();
        BigDecimal carAmount = existingInvoice.getCar().getAmount();

        boolean isReturnDatePassed = carReturnDate.isAfter(bookingDateTo);
        if (isReturnDatePassed) {
            return getMoneyForLateReturn(existingInvoice, carReturnDate, bookingDateTo, bookingDateFrom, carAmount);
        }

        boolean isBeforeReturnDate = carReturnDate.isBefore(bookingDateTo);
        if (isBeforeReturnDate) {
            return getMoneyForReturnBeforeTerm(existingInvoice, carReturnDate, bookingDateFrom, carAmount);
        }

        return carAmount.multiply(BigDecimal.valueOf(getDaysPeriod(bookingDateFrom, bookingDateTo)))
                .add(ObjectUtils.isEmpty(existingInvoice.getDamageCost()) ? BigDecimal.ZERO : existingInvoice.getDamageCost());
    }

    private int getDaysPeriod(LocalDate bookingDateFrom, LocalDate bookingDateTo) {
        int days = Period.between(bookingDateFrom, bookingDateTo).getDays();

        if (days == 0) {
            return 1;
        }

        return days;
    }

    private BigDecimal getMoneyForReturnBeforeTerm(Invoice invoice, LocalDate carReturnDate, LocalDate bookingDateFrom,
                                                   BigDecimal carAmount) {
        return carAmount.multiply(BigDecimal.valueOf(getDaysPeriod(bookingDateFrom, carReturnDate)))
                .add(getDamageCost(invoice));
    }

    private BigDecimal getMoneyForLateReturn(Invoice invoice, LocalDate carReturnDate, LocalDate bookingDateTo,
                                             LocalDate bookingDateFrom, BigDecimal carAmount) {
        return carAmount.multiply(BigDecimal.valueOf(getDaysPeriod(bookingDateFrom, bookingDateTo)))
                .add(carAmount.multiply(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(getDaysPeriod(bookingDateTo, carReturnDate))))
                .add(getDamageCost(invoice));
    }

    private BigDecimal getDamageCost(Invoice existingInvoice) {
        return ObjectUtils.isEmpty(existingInvoice.getDamageCost()) ? BigDecimal.ZERO : existingInvoice.getDamageCost();
    }

}
