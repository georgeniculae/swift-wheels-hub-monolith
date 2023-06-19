package com.carrentalservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Invoice extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee receptionistEmployee;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Booking booking;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date carDateOfReturn;

    private Boolean isVehicleDamaged;

    private Double damageCost;

    private Double additionalPayment;

    private String comments;

}
