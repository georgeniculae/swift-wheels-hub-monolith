package com.carrentalservice.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto extends UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private List<BookingDto> bookingDtoList = new ArrayList<>();

    public CustomerDto() {
    }

    public CustomerDto(Long id, String username, String password, String confirmPassword, String firstName, String lastName, String email, String address, List<BookingDto> bookingDtoList) {
        super(id, username, password, confirmPassword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.bookingDtoList = bookingDtoList;
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

    public List<BookingDto> getBookingDTOList() {
        return bookingDtoList;
    }

    public void setBookingDTOList(List<BookingDto> bookingDTOList) {
        this.bookingDtoList = bookingDTOList;
    }
}
