package com.carrentalservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Booking extends BaseEntity {

    @NotBlank(message = "Date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfBooking;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Car car;

    @NotBlank(message = "Date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @NotEmpty(message = "Date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Rental rental;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ReturnCar returnCar;

    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Branch rentalBranch;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Branch returnBranch;

}
