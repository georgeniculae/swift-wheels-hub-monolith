package com.carrentalservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ReturnCar extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfReturn;

    @OneToMany(mappedBy = "returnCar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    private Double additionalPayment;

    private String comments;

}
