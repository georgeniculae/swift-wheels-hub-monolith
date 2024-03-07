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
public class InvoiceRequest {

    private Long id;
    private CustomerDetails customerDetails;
    private CarDetails carDetails;
    private EmployeeDetails receptionistEmployeeDetails;
    private BookingDetails bookingDetails;
    private LocalDate carDateOfReturn;
    private Boolean isVehicleDamaged;
    private BigDecimal damageCost;
    private BigDecimal additionalPayment;
    private BigDecimal totalAmount;
    private String comments;

}
