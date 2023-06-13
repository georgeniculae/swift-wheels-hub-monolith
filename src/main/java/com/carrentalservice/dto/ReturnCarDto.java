package com.carrentalservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnCarDto extends BaseEntityDto {

    private EmployeeDto employee;
    private Date dateOfReturn;
    private List<BookingDto> bookings = new ArrayList<>();
    private Double additionalPayment;
    private String comments;

    public ReturnCarDto(Long id, EmployeeDto employee, Date dateOfReturn, List<BookingDto> bookings, Double additionalPayment, String comments) {
        super(id);
        this.employee = employee;
        this.dateOfReturn = dateOfReturn;
        this.bookings = bookings;
        this.additionalPayment = additionalPayment;
        this.comments = comments;
    }

    public ReturnCarDto() {
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public List<BookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDto> bookings) {
        this.bookings = bookings;
    }

    public Double getAdditionalPayment() {
        return additionalPayment;
    }

    public void setAdditionalPayment(Double additionalPayment) {
        this.additionalPayment = additionalPayment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
