package com.carrentalservice.service;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.*;
import com.carrentalservice.exception.CarRentalServiceException;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BookingMapper;
import com.carrentalservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final BranchService branchService;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto saveBooking(BookingDto newBookingDto) {
        validateBookingDates(newBookingDto);
        Booking newBooking = bookingMapper.mapDtoToEntity(newBookingDto);

        Customer customer = customerService.getLoggedInCustomer();
        Car car = carService.findEntityById(newBookingDto.getCar().getId());
        car.setCarStatus(CarStatus.NOT_AVAILABLE);
        Branch rentalBranch = branchService.findEntityById(car.getBranch().getId());

        Invoice invoice = setupInvoice(newBooking, customer, car);
        setupNewBooking(newBooking, customer, car, rentalBranch, invoice);

        Booking savedBooking = bookingRepository.save(newBooking);

        return bookingMapper.mapEntityToDto(savedBooking);
    }

    @Transactional
    public BookingDto updateBooking(BookingDto updatedBookingDto) {
        validateBookingDates(updatedBookingDto);
        Booking existingBooking = findEntityById(updatedBookingDto.getId());

        Car car = carService.findEntityById(updatedBookingDto.getCar().getId());

        existingBooking.setDateOfBooking(getCurrentDate());
        existingBooking.setDateFrom(updatedBookingDto.getDateFrom());
        existingBooking.setDateTo(updatedBookingDto.getDateTo());
        existingBooking.setCar(car);
        existingBooking.setRentalBranch(car.getBranch());
        existingBooking.setAmount(getAmount(updatedBookingDto.getDateFrom(), updatedBookingDto.getDateTo(), car.getAmount()));

        Booking savedBooking = bookingRepository.save(existingBooking);

        return bookingMapper.mapEntityToDto(savedBooking);
    }

    @Transactional
    public BookingDto findBookingById(Long id) {
        Booking booking = findEntityById(id);

        return bookingMapper.mapEntityToDto(booking);
    }

    public List<BookingDto> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::mapEntityToDto)
                .toList();
    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Long countBookings() {
        return bookingRepository.count();
    }

    public Long countCustomersWithBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(Booking::getCustomer)
                .distinct()
                .count();
    }

    public BookingDto findBookingByDateOfBooking(String searchString) {
        Optional<Booking> optionalBooking = bookingRepository.findByDateOfBooking(Date.valueOf(searchString));

        if (optionalBooking.isPresent()) {
            return bookingMapper.mapEntityToDto(optionalBooking.get());
        }

        throw new NotFoundException("Booking from date: " + searchString + " does not exist");
    }

    public Long countByLoggedInCustomer() {
        return bookingRepository.countByCustomer(customerService.getLoggedInCustomer());
    }

    public List<BookingDto> findBookingByLoggedInCustomer() {
        return bookingRepository.findBookingsByCustomer(customerService.getLoggedInCustomer())
                .stream()
                .map(bookingMapper::mapEntityToDto)
                .toList();
    }

    public Double getAmountSpentByLoggedInUser() {
        return findBookingByLoggedInCustomer()
                .stream()
                .map(BookingDto::getAmount)
                .reduce(0D, Double::sum);
    }

    public Double getSumOfAllBookingAmount() {
        return findAllBookings()
                .stream()
                .map(BookingDto::getAmount)
                .reduce(0D, Double::sum);
    }

    public Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }

    private void validateBookingDates(BookingDto newBookingDto) {
        LocalDate dateFrom = newBookingDto.getDateFrom().toLocalDate();
        LocalDate dateTo = newBookingDto.getDateTo().toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (dateFrom.isBefore(currentDate) || dateTo.isBefore(currentDate)) {
            throw new CarRentalServiceException(HttpStatus.BAD_REQUEST, "A date of booking cannot be in the past");
        }

        if (dateFrom.isAfter(dateTo)) {
            throw new CarRentalServiceException(HttpStatus.BAD_REQUEST, "Date from is after date to");
        }
    }

    private Double getAmount(Date dateFrom, Date dateTo, Double amount) {
        int bookingDays = Period.between(dateFrom.toLocalDate(), dateTo.toLocalDate()).getDays();

        if (bookingDays == 0) {
            return amount;
        }

        return bookingDays * amount;
    }

    private Booking findEntityById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        }

        throw new NotFoundException("Booking with id " + id + " does not exist");
    }


    private Invoice setupInvoice(Booking newBooking, Customer customer, Car car) {
        Invoice invoice = new Invoice();

        newBooking.setInvoice(invoice);

        invoice.setCustomer(customer);
        invoice.setCar(car);
        invoice.setBooking(newBooking);

        return invoice;
    }

    private void setupNewBooking(Booking newBooking, Customer customer, Car car, Branch rentalBranch, Invoice invoice) {
        newBooking.setCustomer(customer);
        newBooking.setCar(car);
        newBooking.setDateOfBooking(Date.valueOf(LocalDate.now()));
        newBooking.setRentalBranch(rentalBranch);
        newBooking.setStatus(BookingStatus.IN_PROGRESS);
        newBooking.setAmount(getAmount(newBooking.getDateFrom(), newBooking.getDateTo(), car.getAmount()));
        newBooking.setInvoice(invoice);
    }

}
