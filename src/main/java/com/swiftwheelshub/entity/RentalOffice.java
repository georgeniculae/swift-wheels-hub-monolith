package com.swiftwheelshub.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalOffice extends BaseEntity {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Contact address domain cannot be empty")
    private String contactAddress;

    private String phoneNumber;

}
