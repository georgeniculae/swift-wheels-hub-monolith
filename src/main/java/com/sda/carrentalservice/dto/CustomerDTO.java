package com.sda.carrentalservice.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO extends UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private List<BookingDTO> bookingDTOList = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String username, String password, String confirmPassword, String firstName, String lastName, String email, String address, List<BookingDTO> bookingDTOList) {
        super(id, username, password, confirmPassword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.bookingDTOList = bookingDTOList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BookingDTO> getBookingDTOList() {
        return bookingDTOList;
    }

    public void setBookingDTOList(List<BookingDTO> bookingDTOList) {
        this.bookingDTOList = bookingDTOList;
    }
}
