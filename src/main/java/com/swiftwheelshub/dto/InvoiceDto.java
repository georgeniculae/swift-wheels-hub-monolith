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
public class InvoiceDto {

    private Long id;
    private CustomerDto customer;
    private CarDto car;
    private EmployeeDto receptionistEmployee;
    private BookingDto booking;
    private Date carDateOfReturn;
    private Boolean isVehicleDamaged;
    private Double damageCost;
    private Double additionalPayment;
    private Double totalAmount;
    private String comments;

}
