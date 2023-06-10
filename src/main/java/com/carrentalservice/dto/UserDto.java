package com.carrentalservice.dto;

import javax.validation.constraints.NotBlank;

public class UserDto extends BaseEntityDto {

    @NotBlank(message = "Username cannot be blank.")
    private String username;

    @NotBlank(message = "Password cannot be blank.")
    private String password;

    @NotBlank(message = "Confirm password cannot be blank.")
    private String confirmPassword;

    public UserDto(Long id, String username, String password, String confirmPassword) {
        super(id);
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
