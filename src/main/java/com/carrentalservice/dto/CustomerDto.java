package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends UserDto {

    private Date dateOfBirth;
    private String address;

}
