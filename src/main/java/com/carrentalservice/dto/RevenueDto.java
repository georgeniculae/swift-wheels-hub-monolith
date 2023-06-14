package com.carrentalservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RevenueDto extends BaseEntityDto {

    private Double sumOfAmountsForCarRental;
    private Double dailyRevenue;
    private Double weeklyRevenue;
    private Double monthlyRevenue;
    private Double yearlyRevenue;

}
