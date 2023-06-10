package com.carrentalservice.dto;

import java.util.Date;

public class RentalDto extends BaseEntityDto {

    private EmployeeDto employeeOfRentalDto;
    private Date rentalDate;
    private BookingDto bookingDto;
    private String comments;

    public RentalDto(Long id, EmployeeDto employeeOfRentalDto, Date rentalDate, BookingDto bookingDto, String comments) {
        super(id);
        this.employeeOfRentalDto = employeeOfRentalDto;
        this.rentalDate = rentalDate;
        this.bookingDto = bookingDto;
        this.comments = comments;
    }

    public RentalDto() {
    }

    public EmployeeDto getEmployeeOfRentalDto() {
        return employeeOfRentalDto;
    }

    public void setEmployeeOfRentalDto(EmployeeDto employeeOfRentalDto) {
        this.employeeOfRentalDto = employeeOfRentalDto;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public BookingDto getBookingDto() {
        return bookingDto;
    }

    public void setBookingDto(BookingDto bookingDto) {
        this.bookingDto = bookingDto;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
