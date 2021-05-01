package com.sda.carrentalservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Booking extends BaseEntity {

    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfBooking;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Car car;

    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Rental rental;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ReturnCar returnCar;

    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Branch rentalBranch;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Branch returnBranch;

    public Booking(Date dateOfBooking, Customer customer, Car car, Date dateFrom, Date dateTo, Rental rental, ReturnCar returnCar, Double amount, Branch rentalBranch, Branch returnBranch) {
        this.dateOfBooking = dateOfBooking;
        this.customer = customer;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rental = rental;
        this.returnCar = returnCar;
        this.amount = amount;
        this.rentalBranch = rentalBranch;
        this.returnBranch = returnBranch;
    }

    public Booking() {
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public ReturnCar getReturnCar() {
        return returnCar;
    }

    public void setReturnCar(ReturnCar returnCar) {
        this.returnCar = returnCar;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Branch getRentalBranch() {
        return rentalBranch;
    }

    public void setRentalBranch(Branch rentalBranch) {
        this.rentalBranch = rentalBranch;
    }

    public Branch getReturnBranch() {
        return returnBranch;
    }

    public void setReturnBranch(Branch returnBranch) {
        this.returnBranch = returnBranch;
    }
}
