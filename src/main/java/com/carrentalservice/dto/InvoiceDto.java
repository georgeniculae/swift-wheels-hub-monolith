package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class InvoiceDto extends BaseEntityDto {

    private EmployeeDto employee;
    private CarDto car;
    private Date carDateOfReturn;
    private Double additionalPayment;
    private String comments;

}
