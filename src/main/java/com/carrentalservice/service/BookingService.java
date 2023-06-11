package com.carrentalservice.service;

import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Car;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.BookingRepository;
import com.carrentalservice.repository.CarRepository;
import com.carrentalservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public Booking saveUpdatedBooking(Booking booking, Double amountFromCar) {
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
        Booking bookingById = this.findBookingById(id);

        Car car = bookingById.getCar();
        car.getBookingList().remove(bookingById);
        carRepository.save(car);

        Customer customer = bookingById.getCustomer();
        customer.getBookingList().remove(bookingById);
        customerRepository.save(customer);

        bookingRepository.deleteById(id);
    }

    public Long countBookings() {
        return bookingRepository.count();
    }

    public Booking findBookingByName(String searchString) {
        return bookingRepository.findBookingByName(searchString);
    }

    public List<Booking> findBookingByCustomerLoggedIn(Customer customer) {
        return this.bookingRepository.findBookingByCustomer(customer);
    }

    public Long countByCustomer(Customer customer) {
        return this.bookingRepository.countByCustomer(customer);
    }

    public Double calculateAllAmountSpentByUser(Customer customer) {
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
