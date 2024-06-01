package com.swiftwheelshub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RevenueRequest {

    private LocalDate dateOfRevenue;
    private BigDecimal amountFromBooking;

}
