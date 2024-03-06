package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private Role role;

    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmPassword;

    @NotEmpty(message = "Firstname password cannot be empty")
    private String firstName;

    @NotEmpty(message = "Lastname cannot be empty")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

}
