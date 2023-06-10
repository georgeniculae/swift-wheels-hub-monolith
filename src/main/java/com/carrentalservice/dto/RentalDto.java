package com.carrentalservice.dto;

import java.util.Date;

public class RentalDto extends BaseEntityDto {

    private EmployeeDto employeeOfRentalDTO;
    private Date rentalDate;
    private BookingDto bookingDTO;
    private String comments;

    public RentalDto(Long id, EmployeeDto employeeOfRentalDTO, Date rentalDate, BookingDto bookingDTO, String comments) {
        super(id);
        this.employeeOfRentalDTO = employeeOfRentalDTO;
        this.rentalDate = rentalDate;
        this.bookingDTO = bookingDTO;
        this.comments = comments;
    }

    public RentalDto() {
    }

    public EmployeeDto getEmployeeOfRentalDTO() {
        return employeeOfRentalDTO;
    }

    public void setEmployeeOfRentalDTO(EmployeeDto employeeForRentalDTO) {
        this.employeeOfRentalDTO = employeeOfRentalDTO;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public BookingDto getBookingDTO() {
        return bookingDTO;
    }

    public void setBookingList(BookingDto bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
