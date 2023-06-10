package com.carrentalservice.dto;

import com.carrentalservice.entity.Status;

import java.util.List;

public class CarDto extends BaseEntityDto {

    private String make;
    private String model;
    private String bodyType;
    private int yearOfProduction;
    private String color;
    private int mileage;
    private Status status;
    private Double amount;
    private List<BookingDto> bookingListDTO;
    private BranchDto branchDTO;
    private String urlOfImage;

    public CarDto(Long id, String make, String model, String bodyType, int yearOfProduction, String color, int mileage, Status status, Double amount, List<BookingDto> bookingListDTO, BranchDto branchDTO, String urlOfImage) {
        super(id);
        this.make = make;
        this.model = model;
        this.bodyType = bodyType;
        this.yearOfProduction = yearOfProduction;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
        this.amount = amount;
        this.bookingListDTO = bookingListDTO;
        this.branchDTO = branchDTO;
        this.urlOfImage = urlOfImage;
    }

    public CarDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<BookingDto> getBookingListDTO() {
        return bookingListDTO;
    }

    public void setBookingListDTO(List<BookingDto> bookingListDTO) {
        this.bookingListDTO = bookingListDTO;
    }

    public BranchDto getBranchDTO() {
        return branchDTO;
    }

    public void setBranchDTO(BranchDto branchDTO) {
        this.branchDTO = branchDTO;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

    public void setUrlOfImage(String urlOfImage) {
        this.urlOfImage = urlOfImage;
    }
}
