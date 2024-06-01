package com.swiftwheelshub.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalOfficeRequest {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Contact address cannot be empty")
    private String contactAddress;

    private String phoneNumber;

}
