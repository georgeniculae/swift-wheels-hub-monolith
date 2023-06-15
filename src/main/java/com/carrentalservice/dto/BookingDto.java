package com.carrentalservice.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingDto extends BaseEntityDto {

    private Date dateOfBooking;
    private CustomerDto customer;
    private CarDto car;
    private Date dateFrom;
    private Date dateTo;
    private ReturnCarDto returnCar;
    private Double amount;
    private BranchDto rentalBranch;
    private BranchDto returnBranch;

}
