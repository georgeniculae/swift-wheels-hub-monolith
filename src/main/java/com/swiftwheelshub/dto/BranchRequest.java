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
public class BranchRequest {

    private Long id;
    private String name;
    private String address;
    private RentalOfficeDetails rentalOfficeDetails;

}
