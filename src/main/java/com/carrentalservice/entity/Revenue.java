package com.carrentalservice.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Revenue extends BaseEntity {

    private Date dateOfRevenue;
    private Double amountFromBooking;

}
