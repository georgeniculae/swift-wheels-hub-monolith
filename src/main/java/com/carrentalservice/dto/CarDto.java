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
    private List<BookingDto> bookingDtoList;
    private BranchDto branchDto;
    private String urlOfImage;

    public CarDto(Long id, String make, String model, String bodyType, int yearOfProduction, String color, int mileage, Status status, Double amount, List<BookingDto> bookingDtoList, BranchDto branchDto, String urlOfImage) {
        super(id);
        this.make = make;
        this.model = model;
        this.bodyType = bodyType;
        this.yearOfProduction = yearOfProduction;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
        this.amount = amount;
        this.bookingDtoList = bookingDtoList;
        this.branchDto = branchDto;
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

    public List<BookingDto> getBookingDtoList() {
        return bookingDtoList;
    }

    public void setBookingDtoList(List<BookingDto> bookingDtoList) {
        this.bookingDtoList = bookingDtoList;
    }

    public BranchDto getBranchDto() {
        return branchDto;
    }

    public void setBranchDto(BranchDto branchDto) {
        this.branchDto = branchDto;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

    public void setUrlOfImage(String urlOfImage) {
        this.urlOfImage = urlOfImage;
    }

}
