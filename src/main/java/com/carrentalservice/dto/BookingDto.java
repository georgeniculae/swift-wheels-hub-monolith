package com.carrentalservice.dto;

import java.util.Date;

public class BookingDto extends BaseEntityDto {

    private Date dateOfBooking;
    private CustomerDto customerDTO;
    private CarDto carDTO;
    private Date dateFrom;
    private Date dateTo;
    private RentalDto rentalDTO;
    private ReturnCarDto returnCarDTO;
    private Double amount;
    private BranchDto rentalBranchDTO;
    private BranchDto returnBranchDTO;

    public BookingDto(Long id, Date dateOfBooking, CustomerDto customerDTO, CarDto carDTO, Date dateFrom, Date dateTo, RentalDto rentalDTO, ReturnCarDto returnCarDTO, Double amount, BranchDto rentalBranchDTO, BranchDto returnBranchDTO) {
        super(id);
        this.dateOfBooking = dateOfBooking;
        this.customerDTO = customerDTO;
        this.carDTO = carDTO;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rentalDTO = rentalDTO;
        this.returnCarDTO = returnCarDTO;
        this.amount = amount;
        this.rentalBranchDTO = rentalBranchDTO;
        this.returnBranchDTO = returnBranchDTO;
    }

    public BookingDto() {
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public CustomerDto getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDto customerDTO) {
        this.customerDTO = customerDTO;
    }

    public CarDto getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDto carDTO) {
        this.carDTO = carDTO;
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

    public RentalDto getRentalDTO() {
        return rentalDTO;
    }

    public void setRentalDTO(RentalDto rentalDTO) {
        this.rentalDTO = rentalDTO;
    }

    public ReturnCarDto getReturnCarDTO() {
        return returnCarDTO;
    }

    public void setReturnCarDTO(ReturnCarDto returnCarDTO) {
        this.returnCarDTO = returnCarDTO;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BranchDto getRentalBranchDTO() {
        return rentalBranchDTO;
    }

    public void setRentalBranchDTO(BranchDto rentalBranchDTO) {
        this.rentalBranchDTO = rentalBranchDTO;
    }

    public BranchDto getReturnBranchDTO() {
        return returnBranchDTO;
    }

    public void setReturnBranchDTO(BranchDto returnBranchDTO) {
        this.returnBranchDTO = returnBranchDTO;
    }
}
