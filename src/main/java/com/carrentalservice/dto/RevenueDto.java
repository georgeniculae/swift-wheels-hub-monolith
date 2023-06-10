package com.carrentalservice.dto;

public class RevenueDto extends BaseEntityDto {

    private Double sumOfAmountsForCarRental;

    public RevenueDto(Long id, Double sumOfAmountsForCarRental) {
        super(id);
        this.sumOfAmountsForCarRental = sumOfAmountsForCarRental;
    }

    public RevenueDto() {
    }

    public Double getSumOfAmountsForCarRental() {
        return sumOfAmountsForCarRental;
    }

    public void setSumOfAmountsForCarRental(Double sumOfAmountsForCarRental) {
        this.sumOfAmountsForCarRental = sumOfAmountsForCarRental;
    }

}
