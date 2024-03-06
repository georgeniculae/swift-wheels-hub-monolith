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
public class InvoiceDto {

    private Long id;
    private CustomerDto customer;
    private CarDto car;
    private EmployeeDto receptionistEmployee;
    private BookingDto booking;
    private LocalDate carDateOfReturn;
    private Boolean isVehicleDamaged;
    private BigDecimal damageCost;
    private BigDecimal additionalPayment;
    private BigDecimal totalAmount;
    private String comments;

}
