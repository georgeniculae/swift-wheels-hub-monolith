package com.carrentalservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Rental extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Employee employeeOfRental;

    @NotBlank(message = "Date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date rentalDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Booking booking;

    private String comments;

}
