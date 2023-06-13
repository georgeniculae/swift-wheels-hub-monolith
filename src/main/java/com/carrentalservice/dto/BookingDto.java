package com.carrentalservice.dto;

import java.util.Date;

public class BookingDto extends BaseEntityDto {

    private Date dateOfBooking;
    private CustomerDto customer;
    private CarDto car;
    private Date dateFrom;
    private Date dateTo;
    private RentalDto rental;
    private ReturnCarDto returnCar;
    private Double amount;
    private BranchDto rentalBranch;
    private BranchDto returnBranch;

    public BookingDto(Long id, Date dateOfBooking, CustomerDto customer, CarDto car, Date dateFrom, Date dateTo, RentalDto rental, ReturnCarDto returnCar, Double amount, BranchDto rentalBranch, BranchDto returnBranch) {
        super(id);
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

    public BookingDto() {
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
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

    public RentalDto getRental() {
        return rental;
    }

    public void setRental(RentalDto rental) {
        this.rental = rental;
    }

    public ReturnCarDto getReturnCar() {
        return returnCar;
    }

    public void setReturnCar(ReturnCarDto returnCar) {
        this.returnCar = returnCar;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BranchDto getRentalBranch() {
        return rentalBranch;
    }

    public void setRentalBranch(BranchDto rentalBranch) {
        this.rentalBranch = rentalBranch;
    }

    public BranchDto getReturnBranch() {
        return returnBranch;
    }

    public void setReturnBranch(BranchDto returnBranch) {
        this.returnBranch = returnBranch;
    }

}
