package com.carrentalservice.dto;

import java.util.Date;

public class BookingDto extends BaseEntityDto {

    private Date dateOfBooking;
    private CustomerDto customerDto;
    private CarDto carDTO;
    private Date dateFrom;
    private Date dateTo;
    private RentalDto rentalDto;
    private ReturnCarDto returnCarDto;
    private Double amount;
    private BranchDto rentalBranchDto;
    private BranchDto returnBranchDto;

    public BookingDto(Long id, Date dateOfBooking, CustomerDto customerDto, CarDto carDTO, Date dateFrom, Date dateTo, RentalDto rentalDto, ReturnCarDto returnCarDto, Double amount, BranchDto rentalBranchDto, BranchDto returnBranchDto) {
        super(id);
        this.dateOfBooking = dateOfBooking;
        this.customerDto = customerDto;
        this.carDTO = carDTO;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rentalDto = rentalDto;
        this.returnCarDto = returnCarDto;
        this.amount = amount;
        this.rentalBranchDto = rentalBranchDto;
        this.returnBranchDto = returnBranchDto;
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
        return customerDto;
    }

    public void setCustomerDTO(CustomerDto customerDTO) {
        this.customerDto = customerDTO;
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
        return rentalDto;
    }

    public void setRentalDTO(RentalDto rentalDTO) {
        this.rentalDto = rentalDTO;
    }

    public ReturnCarDto getReturnCarDTO() {
        return returnCarDto;
    }

    public void setReturnCarDTO(ReturnCarDto returnCarDTO) {
        this.returnCarDto = returnCarDTO;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BranchDto getRentalBranchDTO() {
        return rentalBranchDto;
    }

    public void setRentalBranchDTO(BranchDto rentalBranchDTO) {
        this.rentalBranchDto = rentalBranchDTO;
    }

    public BranchDto getReturnBranchDTO() {
        return returnBranchDto;
    }

    public void setReturnBranchDTO(BranchDto returnBranchDTO) {
        this.returnBranchDto = returnBranchDTO;
    }
}
