package com.sda.carrentalservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ReturnCar extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfReturn;

    @OneToMany(mappedBy = "returnCar", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookingList = new ArrayList<>();

    private Double additionalPayment;
    private String comments;

    public ReturnCar(Employee employee, Date dateOfReturn, List<Booking> bookingList, Double additionalPayment, String comments) {
        this.employee = employee;
        this.dateOfReturn = dateOfReturn;
        this.bookingList = bookingList;
        this.additionalPayment = additionalPayment;
        this.comments = comments;
    }

    public ReturnCar() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date rentalDate) {
        this.dateOfReturn = rentalDate;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getAdditionalPayment() {
        return additionalPayment;
    }

    public void setAdditionalPayment(Double additionalPayment) {
        this.additionalPayment = additionalPayment;
    }
}
