package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class InvoiceDto extends BaseEntityDto {

    private Long customerId;
    private Long carId;
    private Long receptionistEmployeeId;
    private Long bookingId;
    private Date carDateOfReturn;
    private Boolean isVehicleDamaged;
    private Double damageCost;
    private Double additionalPayment;
    private Double totalAmount;
    private String comments;

}
