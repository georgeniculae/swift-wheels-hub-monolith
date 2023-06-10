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

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
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

    public RentalDto getRentalDto() {
        return rentalDto;
    }

    public void setRentalDto(RentalDto rentalDto) {
        this.rentalDto = rentalDto;
    }

    public ReturnCarDto getReturnCarDto() {
        return returnCarDto;
    }

    public void setReturnCarDto(ReturnCarDto returnCarDto) {
        this.returnCarDto = returnCarDto;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BranchDto getRentalBranchDto() {
        return rentalBranchDto;
    }

    public void setRentalBranchDto(BranchDto rentalBranchDto) {
        this.rentalBranchDto = rentalBranchDto;
    }

    public BranchDto getReturnBranchDto() {
        return returnBranchDto;
    }

    public void setReturnBranchDto(BranchDto returnBranchDto) {
        this.returnBranchDto = returnBranchDto;
    }

}
