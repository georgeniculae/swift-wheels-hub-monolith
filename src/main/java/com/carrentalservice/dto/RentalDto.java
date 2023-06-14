package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RentalDto extends BaseEntityDto {

    private EmployeeDto employeeOfRental;
    private Date rentalDate;
    private BookingDto booking;
    private String comments;

}
