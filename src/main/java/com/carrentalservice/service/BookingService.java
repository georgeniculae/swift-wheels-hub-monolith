package com.carrentalservice.service;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Car;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BookingMapper;
import com.carrentalservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Period;
import java.util.ArrayList;
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
        Booking newBooking = bookingMapper.mapDtoToEntity(newBookingDto);

        Customer customer = customerService.getLoggedInCustomer();
        Car car = carService.findEntityById(newBookingDto.getCar().getId());
        Branch rentalBranch = branchService.findEntityById(car.getBranch().getId());

        newBooking.setCustomer(customer);
        newBooking.setCar(car);
        newBooking.setRentalBranch(rentalBranch);

        Booking savedBooking = saveBookingWithCalculatedAmount(newBooking, car.getAmount());

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

    @Transactional
    public void deleteBookingById(Long id) {
        Booking existingBooking = findEntityById(id);

        Car car = existingBooking.getCar();
        car.getBookings().remove(existingBooking);
        carService.saveEntity(car);

        Customer customer = existingBooking.getCustomer();

        ArrayList<Booking> allBookings = new ArrayList<>(customer.getBookings());
        allBookings.remove(existingBooking);
        customer.setBookings(allBookings);
        customerService.saveEntity(customer);

        bookingRepository.deleteById(id);
    }

    public Long countBookings() {
        return bookingRepository.count();
    }

    public BookingDto findBookingByName(String searchString) {
        Booking booking = bookingRepository.findBookingByName(Date.valueOf(searchString));

        return bookingMapper.mapEntityToDto(booking);
    }

    public BookingDto updateBooking(BookingDto newBookingDto) {
        Booking newBooking = bookingMapper.mapDtoToEntity(newBookingDto);

        Booking existingBooking = findEntityById(newBookingDto.getId());
        Car car = carService.findEntityById(newBookingDto.getCar().getId());

        existingBooking.setDateOfBooking(newBooking.getDateOfBooking());
        existingBooking.setDateFrom(newBooking.getDateFrom());
        existingBooking.setDateTo(newBookingDto.getDateTo());
        existingBooking.setCar(car);
        existingBooking.setRentalBranch(car.getBranch());
        existingBooking.setAmount(getAmount(newBooking, car.getAmount()));

        Booking savedBooking = bookingRepository.save(existingBooking);

        return bookingMapper.mapEntityToDto(savedBooking);
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

    private Booking saveBookingWithCalculatedAmount(Booking booking, Double amount) {
        booking.setAmount(getAmount(booking, amount));

        return bookingRepository.save(booking);
    }

    private Double getAmount(Booking booking, Double amount) {
        int bookingDays = Period.between(booking.getDateFrom().toLocalDate(), booking.getDateTo().toLocalDate()).getDays();

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

}
