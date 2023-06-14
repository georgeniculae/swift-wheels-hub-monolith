package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ReturnCarDto extends BaseEntityDto {

    private EmployeeDto employee;
    private Date dateOfReturn;
    private List<BookingDto> bookings = new ArrayList<>();
    private Double additionalPayment;
    private String comments;

}
