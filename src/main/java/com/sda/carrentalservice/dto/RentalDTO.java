package com.sda.carrentalservice.dto;

import java.util.Date;

public class RentalDTO extends BaseEntityDTO {

    private EmployeeDTO employeeOfRentalDTO;
    private Date rentalDate;
    private BookingDTO bookingDTO;
    private String comments;

    public RentalDTO(Long id, EmployeeDTO employeeOfRentalDTO, Date rentalDate, BookingDTO bookingDTO, String comments) {
        super(id);
        this.employeeOfRentalDTO = employeeOfRentalDTO;
        this.rentalDate = rentalDate;
        this.bookingDTO = bookingDTO;
        this.comments = comments;
    }

    public RentalDTO() {
    }

    public EmployeeDTO getEmployeeOfRentalDTO() {
        return employeeOfRentalDTO;
    }

    public void setEmployeeOfRentalDTO(EmployeeDTO employeeForRentalDTO) {
        this.employeeOfRentalDTO = employeeOfRentalDTO;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public void setBookingList(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
