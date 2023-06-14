package com.carrentalservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<BookingDto> bookings = new ArrayList<>();

}
