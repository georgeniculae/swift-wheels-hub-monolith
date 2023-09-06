package com.carrentalservice.dto;

import com.carrentalservice.entity.BookingStatus;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingDto extends BaseEntityDto {

    private Date dateOfBooking;
    private BookingStatus status;
    private Long customerId;
    private Long carId;
    private Date dateFrom;
    private Date dateTo;
    private Double amount;
    private Long rentalBranchId;
    private Long returnBranchId;

}
