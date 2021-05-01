package com.sda.carrentalservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car extends BaseEntity {

    private String make;
    private String model;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    private int yearOfProduction;
    private String color;
    private int mileage;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double amount;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookingList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch = new Branch();

    private String urlOfImage;

    public Car(String make, String model, BodyType bodyType, int yearOfProduction, String color, int mileage, Status status, Double amount, Branch branch, String urlOfImage) {
        this.make = make;
        this.model = model;
        this.bodyType = bodyType;
        this.yearOfProduction = yearOfProduction;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
        this.amount = amount;
        this.branch = branch;
        this.urlOfImage = urlOfImage;
    }

    public Car() {
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

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
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

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

    public void setUrlOfImage(String urlOfImage) {
        this.urlOfImage = urlOfImage;
    }
}
