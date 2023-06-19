package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class InvoiceDto extends BaseEntityDto {

    private CustomerDto customer;
    private CarDto car;
    private BookingDto booking;
    private EmployeeDto receptionistEmployee;
    private Date carDateOfReturn;
    private Boolean isVehicleDamaged;
    private Double damageCost;
    private Double additionalPayment;
    private String comments;

}
