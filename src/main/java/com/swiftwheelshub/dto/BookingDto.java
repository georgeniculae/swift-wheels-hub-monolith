package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDto {

    private Long id;
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
