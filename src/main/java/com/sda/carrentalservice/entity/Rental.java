package com.sda.carrentalservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Rental extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Employee employeeOfRental;

    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date rentalDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Booking booking;

    private String comments;

    public Rental(Employee employeeOfRental, Date rentalDate, Booking booking, String comments) {
        this.employeeOfRental = employeeOfRental;
        this.rentalDate = rentalDate;
        this.booking = booking;
        this.comments = comments;
    }

    public Rental() {
    }

    public Employee getEmployeeOfRental() {
        return employeeOfRental;
    }

    public void setEmployeeOfRental(Employee employeeOfRental) {
        this.employeeOfRental = employeeOfRental;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
