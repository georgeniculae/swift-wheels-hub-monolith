package com.carrentalservice.service;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.BookingStatus;
import com.carrentalservice.entity.Car;
import com.carrentalservice.entity.CarStatus;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.entity.Invoice;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.exception.CarRentalServiceException;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.InvoiceMapper;
import com.carrentalservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final EmployeeService employeeService;
    private final RevenueService revenueService;
    private final CarService carService;
    private final InvoiceMapper invoiceMapper;

    public InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDto) {
        Long actualId = getId(id, invoiceDto.getId());

        Invoice existingInvoice = findEntityById(actualId);
        validateInvoice(invoiceDto, existingInvoice.getBooking().getDateFrom());

        Employee receptionistEmployee = employeeService.findEntityById(invoiceDto.getReceptionistEmployee().getId());
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

        setupRevenue(existingInvoice);

        Invoice savedInvoice = invoiceRepository.save(existingInvoice);

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
        Optional<Invoice> optionalInvoice = invoiceRepository.findInvoiceByComments(searchString);

        if (optionalInvoice.isPresent()) {
            return invoiceMapper.mapEntityToDto(optionalInvoice.get());
        }

        throw new NotFoundException("Invoice with comment: " + searchString + " does not exist");
    }

    public Long countInvoices() {
        return invoiceRepository.count();
    }

    public long countAllActiveInvoices() {
        return invoiceRepository.countAllActiveInvoices();
    }

    public Invoice findEntityById(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        }

        throw new NotFoundException("Invoice with id " + id + " does not exist");
    }

    private void validateInvoice(InvoiceDto invoiceDto, Date dateFrom) {
        validateDateOfReturnOfTheCar(invoiceDto.getCarDateOfReturn(), dateFrom);

        if (invoiceDto.getIsVehicleDamaged() && ObjectUtils.isEmpty(invoiceDto.getDamageCost())) {
            throw new CarRentalServiceException(
                    HttpStatus.BAD_REQUEST,
                    "If the vehicle is damaged, the damage cost cannot be null/empty"
            );
        }
    }

    private void validateDateOfReturnOfTheCar(Date dateOfReturnOfTheCar, Date dateFrom) {
        LocalDate dateOfReturnOfTheCarAsLocalDate = dateOfReturnOfTheCar.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (dateFrom.toLocalDate().isAfter(currentDate) &&
                (currentDate.isBefore(dateOfReturnOfTheCarAsLocalDate) || currentDate.equals(dateOfReturnOfTheCarAsLocalDate))) {
            throw new CarRentalServiceException(
                    HttpStatus.BAD_REQUEST,
                    "The booking is not started yet"
            );
        }

        if (dateOfReturnOfTheCarAsLocalDate.isBefore(currentDate)) {
            throw new CarRentalServiceException(
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

    private void setupRevenue(Invoice existingInvoice) {
        Revenue revenue = new Revenue();

        revenue.setDateOfRevenue(existingInvoice.getCarDateOfReturn());
        revenue.setAmountFromBooking(existingInvoice.getTotalAmount());

        revenueService.saveEntity(revenue);
    }

    private Double getTotalAmount(Invoice existingInvoice, Booking booking) {
        LocalDate carReturnDate = existingInvoice.getCarDateOfReturn().toLocalDate();
        LocalDate bookingDateTo = booking.getDateTo().toLocalDate();
        LocalDate bookingDateFrom = booking.getDateFrom().toLocalDate();
        Double carAmount = existingInvoice.getCar().getAmount();

        boolean isReturnDatePassed = carReturnDate.isAfter(bookingDateTo);
        if (isReturnDatePassed) {
            return getMoneyForLateReturn(existingInvoice, carReturnDate, bookingDateTo, bookingDateFrom, carAmount);
        }

        boolean isBeforeReturnDate = carReturnDate.isBefore(bookingDateTo);
        if (isBeforeReturnDate) {
            return getMoneyForReturnBeforeTerm(existingInvoice, carReturnDate, bookingDateFrom, carAmount);
        }

        return getDaysPeriod(bookingDateFrom, bookingDateTo) * carAmount +
                (ObjectUtils.isEmpty(existingInvoice.getDamageCost()) ? 0D : existingInvoice.getDamageCost());
    }

    private int getDaysPeriod(LocalDate bookingDateFrom, LocalDate bookingDateTo) {
        int days = Period.between(bookingDateFrom, bookingDateTo).getDays();

        if (days == 0) {
            return 1;
        }

        return days;
    }

    private Double getMoneyForReturnBeforeTerm(Invoice invoice, LocalDate carReturnDate, LocalDate bookingDateFrom,
                                               Double carAmount) {
        return getDaysPeriod(bookingDateFrom, carReturnDate) * carAmount + getDamageCost(invoice);
    }

    private double getMoneyForLateReturn(Invoice invoice, LocalDate carReturnDate, LocalDate bookingDateTo,
                                         LocalDate bookingDateFrom, Double carAmount) {
        return getDaysPeriod(bookingDateFrom, bookingDateTo) * carAmount +
                getDaysPeriod(bookingDateTo, carReturnDate) * 2 * carAmount + getDamageCost(invoice);
    }

    private double getDamageCost(Invoice existingInvoice) {
        return ObjectUtils.isEmpty(existingInvoice.getDamageCost()) ? 0D : existingInvoice.getDamageCost();
    }

}
