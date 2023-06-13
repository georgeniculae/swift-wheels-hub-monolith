package com.carrentalservice.dto;

import java.util.Date;

public class RentalDto extends BaseEntityDto {

    private EmployeeDto employeeOfRental;
    private Date rentalDate;
    private BookingDto booking;
    private String comments;

    public RentalDto(Long id, EmployeeDto employeeOfRental, Date rentalDate, BookingDto booking, String comments) {
        super(id);
        this.employeeOfRental = employeeOfRental;
        this.rentalDate = rentalDate;
        this.booking = booking;
        this.comments = comments;
    }

    public RentalDto() {
    }

    public EmployeeDto getEmployeeOfRental() {
        return employeeOfRental;
    }

    public void setEmployeeOfRental(EmployeeDto employeeOfRental) {
        this.employeeOfRental = employeeOfRental;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public BookingDto getBooking() {
        return booking;
    }

    public void setBooking(BookingDto booking) {
        this.booking = booking;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
