package com.carrentalservice.service;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.*;
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
    private final InvoiceMapper invoiceMapper;

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto) {
        validateInvoice(invoiceDto);
        Invoice existingInvoice = findEntityById(invoiceDto.getId());

        Employee receptionistEmployee = employeeService.findEntityById(invoiceDto.getReceptionistEmployee().getId());

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

    public InvoiceDto findInvoiceByName(String searchString) {
        Invoice invoice = invoiceRepository.findInvoiceByName(searchString);

        return invoiceMapper.mapEntityToDto(invoice);
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

        throw new NotFoundException("Invoice with id " + id + "does not exist");
    }

    private void validateInvoice(InvoiceDto invoiceDto) {
        validateDateOfReturnOfTheCar(invoiceDto.getCarDateOfReturn());

        if (invoiceDto.getIsVehicleDamaged() && ObjectUtils.isEmpty(invoiceDto.getDamageCost())) {
            throw new CarRentalServiceException(
                    HttpStatus.BAD_REQUEST,
                    "If the vehicle is damaged, the damage cost cannot be null/empty"
            );
        }
    }

    private void validateDateOfReturnOfTheCar(Date dateOfReturnOfTheCar) {
        LocalDate dateOfReturnOfTheCarAsLocalDate = dateOfReturnOfTheCar.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (dateOfReturnOfTheCarAsLocalDate.isBefore(currentDate)) {
            throw new CarRentalServiceException(
                    HttpStatus.BAD_REQUEST,
                    "Date of return of the car cannot be in the past"
            );
        }
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
            return getMoneyForLateReturn(carReturnDate, bookingDateTo, bookingDateFrom, carAmount);
        }

        return getDaysPeriod(bookingDateFrom, bookingDateTo) * carAmount + existingInvoice.getDamageCost();
    }

    private int getDaysPeriod(LocalDate bookingDateFrom, LocalDate bookingDateTo) {
        return Period.between(bookingDateFrom, bookingDateTo).getDays();
    }

    private double getMoneyForLateReturn(LocalDate carReturnDate, LocalDate bookingDateTo, LocalDate bookingDateFrom,
                                         Double carAmount) {
        return getDaysPeriod(bookingDateFrom, bookingDateTo) * carAmount + getDaysPeriod(bookingDateTo, carReturnDate) * 2 * carAmount;
    }

}
