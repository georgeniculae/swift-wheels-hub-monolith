package com.sda.carrentalservice.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculator {

    private Double sum;

    public Calculator() {
    }

    public Calculator(Double sum) {
        this.sum = sum;
    }

    public void calculateAmountForBooking(Booking booking, Car carById) {
        long numberOfDaysForBooking = (booking.getDateTo().getTime() - booking.getDateFrom().getTime()) / (1000 * 60 * 60 * 24);
        booking.setAmount(carById.getAmount() * (double) numberOfDaysForBooking);
    }

    public Double getSumOfAmountOfBookingsOfCustomerLoggedIn(List<Booking> bookingByCustomerLoggedIn) {
        sum = 0.0;
        for (Booking booking : bookingByCustomerLoggedIn) {
            Double amount = booking.getAmount();
            sum += amount;
        }
        return sum;
    }

    public Double getSumOfAllBookingAmount(List<Booking> allBookings) {
        sum = 0.0;
        for (Booking booking : allBookings) {
            Double amount = booking.getAmount();
            sum += amount;
        }
        return sum;
    }
}
