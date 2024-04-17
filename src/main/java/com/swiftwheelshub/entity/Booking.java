package com.swiftwheelshub.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking extends BaseEntity {

    @NotNull(message = "Date of booking cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBooking;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @NotEmpty(message = "Username cannot be empty")
    private String customerUsername;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @NotNull(message = "Date from cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dateFrom;

    @NotNull(message = "Date to cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dateTo;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "rental_branch_id")
    private Branch rentalBranch;

    @ManyToOne
    @JoinColumn(name = "return_branch_id")
    private Branch returnBranch;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.PERSIST)
    private Invoice invoice;

}
