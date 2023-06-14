package com.carrentalservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
