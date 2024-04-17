package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.dto.UpdateCarRequest;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.BookingStatus;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.CarStatus;
import com.swiftwheelshub.entity.Invoice;
import com.swiftwheelshub.exception.SwiftWheelsHubException;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.exception.SwiftWheelsHubResponseStatusException;
import com.swiftwheelshub.mapper.BookingMapper;
import com.swiftwheelshub.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarService carService;
    private final BranchService branchService;
    private final AuthenticationInfoExtractorService authenticationInfoExtractorService;
    private final BookingMapper bookingMapper;

    public BookingResponse saveBooking(BookingRequest newBookingRequest) {
        validateBookingDates(newBookingRequest);
        Booking newBooking = bookingMapper.mapDtoToEntity(newBookingRequest);

        Car car = carService.findEntityById(newBookingRequest.getCarDetails().getId());
        car.setCarStatus(CarStatus.NOT_AVAILABLE);
        Branch rentalBranch = branchService.findEntityById(car.getActualBranch().getId());

        Invoice invoice = setupInvoice(newBooking, car);
        Booking setupBooking = setupNewBooking(newBooking, car, rentalBranch, invoice);

        Booking savedBooking = bookingRepository.save(setupBooking);

        return bookingMapper.mapEntityToDto(savedBooking);
    }

    public BookingResponse updateBooking(Long id, BookingRequest updatedBookingRequest) {
        validateBookingDates(updatedBookingRequest);
        Booking existingBooking = findEntityById(id);

        Car car = carService.findEntityById(updatedBookingRequest.getCarDetails().getId());

        existingBooking.setDateOfBooking(getCurrentDate());
        existingBooking.setDateFrom(updatedBookingRequest.getDateFrom());
        existingBooking.setDateTo(updatedBookingRequest.getDateTo());
        existingBooking.setCar(car);
        existingBooking.setRentalBranch(car.getActualBranch());
        existingBooking.setAmount(getAmount(updatedBookingRequest.getDateFrom(), updatedBookingRequest.getDateTo(), car.getAmount()));

        Booking savedBooking = bookingRepository.save(existingBooking);

        return bookingMapper.mapEntityToDto(savedBooking);
    }

    public BookingResponse findBookingById(Long id) {
        Booking booking = findEntityById(id);

        return bookingMapper.mapEntityToDto(booking);
    }

    public List<BookingResponse> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::mapEntityToDto)
                .toList();
    }

    public void deleteBookingByCustomerUsername(String username) {
        List<Booking> existingBookings = bookingRepository.findByCustomerUsername(username);

        try {
            bookingRepository.deleteByCustomerUsername(username);
        } catch (Exception e) {
            throw new SwiftWheelsHubException(e.getMessage());
        }

        carService.updateCarsStatus(getUpdateCarRequests(existingBookings));
    }

    public Long countBookings() {
        return bookingRepository.count();
    }

    public Long countCustomersWithBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(Booking::getCustomerUsername)
                .distinct()
                .count();
    }

    public BookingResponse findBookingByDateOfBooking(String filter) {
        return bookingRepository.findByDateOfBooking(LocalDate.parse(filter))
                .map(bookingMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Booking from date: " + filter + " does not exist"));
    }

    public Long countByLoggedInCustomer() {
        String username = authenticationInfoExtractorService.getUsername();

        return bookingRepository.countByCustomerUsername(username);
    }

    public List<BookingResponse> findBookingsByLoggedInCustomer() {
        String username = authenticationInfoExtractorService.getUsername();

        return bookingRepository.findByCustomerUsername(username)
                .stream()
                .map(bookingMapper::mapEntityToDto)
                .toList();
    }

    public BigDecimal getAmountSpentByLoggedInUser() {
        return findBookingsByLoggedInCustomer()
                .stream()
                .map(BookingResponse::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSumOfAllBookingAmount() {
        return findAllBookings()
                .stream()
                .map(BookingResponse::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    private void validateBookingDates(BookingRequest newBookingRequest) {
        LocalDate dateFrom = newBookingRequest.getDateFrom();
        LocalDate dateTo = newBookingRequest.getDateTo();
        LocalDate currentDate = LocalDate.now();

        if (dateFrom.isBefore(currentDate) || dateTo.isBefore(currentDate)) {
            throw new SwiftWheelsHubResponseStatusException(HttpStatus.BAD_REQUEST, "A date of booking cannot be in the past");
        }

        if (dateFrom.isAfter(dateTo)) {
            throw new SwiftWheelsHubResponseStatusException(HttpStatus.BAD_REQUEST, "Date from is after date to");
        }
    }

    private BigDecimal getAmount(LocalDate dateFrom, LocalDate dateTo, BigDecimal amount) {
        int bookingDays = Period.between(dateFrom, dateTo).getDays();

        if (bookingDays == 0) {
            return amount;
        }

        return amount.multiply(BigDecimal.valueOf(bookingDays));
    }

    private Booking findEntityById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Booking with id " + id + " does not exist"));
    }

    private Invoice setupInvoice(Booking newBooking, Car car) {
        Invoice invoice = new Invoice();

        newBooking.setInvoice(invoice);

        invoice.setCustomerUsername(authenticationInfoExtractorService.getUsername());
        invoice.setCar(car);
        invoice.setBooking(newBooking);

        return invoice;
    }

    private Booking setupNewBooking(Booking newBooking, Car car, Branch rentalBranch, Invoice invoice) {
        newBooking.setCustomerUsername(authenticationInfoExtractorService.getUsername());
        newBooking.setCar(car);
        newBooking.setDateOfBooking(LocalDate.now());
        newBooking.setRentalBranch(rentalBranch);
        newBooking.setStatus(BookingStatus.IN_PROGRESS);
        newBooking.setAmount(getAmount(newBooking.getDateFrom(), newBooking.getDateTo(), car.getAmount()));
        newBooking.setInvoice(invoice);

        return newBooking;
    }

    private List<UpdateCarRequest> getUpdateCarRequests(List<Booking> existingBookings) {
        return existingBookings.stream()
                .map(booking -> UpdateCarRequest.builder()
                        .carId(booking.getCar().getId())
                        .carState(CarStatus.AVAILABLE)
                        .build())
                .toList();
    }

}
