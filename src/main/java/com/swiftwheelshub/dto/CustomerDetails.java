package com.swiftwheelshub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomerDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
