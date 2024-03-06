package com.swiftwheelshub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RevenueDto {

    private Long id;
    private Date dateOfRevenue;
    private Double amountFromBooking;

}
