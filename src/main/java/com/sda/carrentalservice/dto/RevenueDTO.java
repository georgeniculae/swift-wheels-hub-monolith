package com.sda.carrentalservice.dto;

public class RevenueDTO extends BaseEntityDTO {

    private Double sumOfAmountsForCarRental;

    public RevenueDTO(Long id, Double sumOfAmountsForCarRental) {
        super(id);
        this.sumOfAmountsForCarRental = sumOfAmountsForCarRental;
    }

    public RevenueDTO() {
    }

    public Double getSumOfAmountsForCarRental() {
        return sumOfAmountsForCarRental;
    }

    public void setSumOfAmountsForCarRental(Double sumOfAmountsForCarRental) {
        this.sumOfAmountsForCarRental = sumOfAmountsForCarRental;
    }
}
