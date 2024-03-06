package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BookingStatus;
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
public class BookingDto {

    private Long id;
    private LocalDate dateOfBooking;
    private BookingStatus status;
    private CustomerDto customer;
    private CarDto car;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private BigDecimal amount;
    private BranchDto rentalBranch;
    private BranchDto returnBranch;

}
