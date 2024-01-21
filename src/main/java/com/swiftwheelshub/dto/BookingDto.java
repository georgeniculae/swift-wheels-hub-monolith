package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingDto extends BaseEntityDto {

    private Date dateOfBooking;
    private BookingStatus status;
    private CustomerDto customer;
    private CarDto car;
    private Date dateFrom;
    private Date dateTo;
    private Double amount;
    private BranchDto rentalBranch;
    private BranchDto returnBranch;

}
