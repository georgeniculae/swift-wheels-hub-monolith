package com.carrentalservice.service;

import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Car;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CarService carService, CustomerService customerService) {
        this.bookingRepository = bookingRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    public Booking saveBookingUpdatedWithCustomerAndCar(Booking booking) {
        Customer customer = customerService.getCustomerLoggedIn();
        Car carById = carService.findCarById(booking.getCar().getId());
        booking.setCustomer(customer);
        booking.setCar(carById);

        return saveBookingWithCalculatedAmount(booking, carById.getAmount());
    }

    public Booking saveBookingWithUpdatedCar(Booking booking) {
        Car carById = carService.findCarById(booking.getCar().getId());
        booking.setCar(carById);

        return saveBookingWithCalculatedAmount(booking, carById.getAmount());
    }

    public Booking saveBookingWithCalculatedAmount(Booking booking, Double amountFromCar) {
        double numberOfDaysForBooking = (double) (booking.getDateTo().getTime() - booking.getDateFrom().getTime()) / (1000 * 60 * 60 * 24);
        booking.setAmount(amountFromCar * numberOfDaysForBooking);

        return saveBooking(booking);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking findBookingById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        }

        throw new NotFoundException("Booking with id " + id + " does not exist.");
    }

    public void deleteBookingById(Long id) {
        Booking bookingById = findBookingById(id);

        Car car = bookingById.getCar();
        car.getBookingList().remove(bookingById);
        carService.saveCar(car);

        Customer customer = bookingById.getCustomer();
        customer.getBookingList().remove(bookingById);
        customerService.saveCustomer(customer);

        bookingRepository.deleteById(id);
    }

    public Long countBookings() {
        return bookingRepository.count();
    }

    public Booking findBookingByName(String searchString) {
        return bookingRepository.findBookingByName(Date.valueOf(searchString));
    }

    public Booking updateBooking(Booking newBooking) {
        Booking existingBooking = findBookingById(newBooking.getId());
        newBooking.setId(existingBooking.getId());

        return saveBooking(newBooking);
    }

    public Long countByCustomer(Customer customer) {
        return bookingRepository.countByCustomer(customer);
    }

    public List<Booking> findBookingByCustomerLoggedIn(Customer customerLoggedIn) {
        return bookingRepository.findBookingByCustomer(customerLoggedIn);
    }

    public Double getAmountSpentByUser(Customer customer) {
        return findBookingByCustomerLoggedIn(customer)
                .stream()
                .map(Booking::getAmount)
                .reduce(0D, Double::sum);
    }

    public Double getSumOfAllBookingAmount() {
        return findAllBookings()
                .stream()
                .map(Booking::getAmount)
                .reduce(0D, Double::sum);
    }

}
