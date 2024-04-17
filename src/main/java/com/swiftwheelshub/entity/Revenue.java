package com.swiftwheelshub.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Revenue extends BaseEntity {

    private LocalDate dateOfRevenue;
    private BigDecimal amountFromBooking;

}
