package com.carrentalservice.dto;

import com.carrentalservice.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseEntityDto {

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
