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

    public CustomerDto getCustomerDto() {
        return customer;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customer = customerDto;
    }

    public CarDto getCarDto() {
        return car;
    }

    public void setCarDto(CarDto carDto) {
        this.car = carDto;
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

    public RentalDto getRentalDto() {
        return rental;
    }

    public void setRentalDto(RentalDto rentalDto) {
        this.rental = rentalDto;
    }

    public ReturnCarDto getReturnCarDto() {
        return returnCar;
    }

    public void setReturnCarDto(ReturnCarDto returnCarDto) {
        this.returnCar = returnCarDto;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BranchDto getRentalBranchDto() {
        return rentalBranch;
    }

    public void setRentalBranchDto(BranchDto rentalBranchDto) {
        this.rentalBranch = rentalBranchDto;
    }

    public BranchDto getReturnBranchDto() {
        return returnBranch;
    }

    public void setReturnBranchDto(BranchDto returnBranchDto) {
        this.returnBranch = returnBranchDto;
    }

}
