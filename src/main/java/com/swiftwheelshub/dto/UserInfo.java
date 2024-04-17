package com.swiftwheelshub.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo {

    private String id;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + "\n" +
                "email='" + email + "\n" +
                "firstName='" + firstName + "\n" +
                "lastName='" + lastName + "\n" +
                "address='" + address + "\n" +
                "dateOfBirth=" + dateOfBirth + "\n" +
                "}";
    }

}
