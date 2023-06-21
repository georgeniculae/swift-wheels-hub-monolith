package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RevenueDto extends BaseEntityDto {

    private Date dateOfRevenue;
    private Double amountFromBooking;

}
