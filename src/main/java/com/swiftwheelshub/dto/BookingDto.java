package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookingDto {

    private Long id;
    private LocalDate dateOfBooking;
    private BookingStatus status;
    private CustomerDetails customerDetails;
    private CarDetails carDetails;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private BigDecimal amount;
    private Long rentalBranchId;
    private Long returnBranchId;

}
