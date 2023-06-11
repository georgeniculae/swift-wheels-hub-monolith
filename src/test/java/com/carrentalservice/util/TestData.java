package com.carrentalservice.util;

import com.carrentalservice.entity.*;

import java.time.Instant;
import java.util.Date;

public class TestData {

    public static Booking createBooking() {
        Booking booking = new Booking();

        booking.setId(1L);
        booking.setDateOfBooking(Date.from(Instant.now()));
        booking.setCar(createCar());
        booking.setCustomer(createCustomer());
        booking.setDateFrom(Date.from(Instant.now()));
        booking.setDateTo(Date.from(Instant.now()));
        booking.setRental(createRental());
        booking.setReturnCar(createReturnCar());
        booking.setAmount(50D);
        booking.setRentalBranch(createRentalBranch());
        booking.setReturnBranch(createReturnBranch());

        return booking;
    }

    public static Car createCar() {
        Car car = new Car();

        car.setId(1L);
        car.setAmount(100D);

        return car;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();

        customer.setId(1L);

        return customer;
    }

    private static Rental createRental() {
        Rental rental = new Rental();

        rental.setId(1L);

        return rental;
    }

    private static ReturnCar createReturnCar() {
        ReturnCar returnCar = new ReturnCar();

        returnCar.setId(1L);

        return returnCar;
    }

    private static Branch createRentalBranch() {
        Branch branch = new Branch();

        branch.setId(1L);

        return branch;
    }

    private static Branch createReturnBranch() {
        Branch branch = new Branch();

        branch.setId(1L);

        return branch;
    }

}
