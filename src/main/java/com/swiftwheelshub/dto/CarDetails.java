package com.swiftwheelshub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CarDetails {

    private Long id;
    private String make;
    private String model;
    private String color;
    private BigDecimal amount;
    private BranchDetails branchDetails;

}
