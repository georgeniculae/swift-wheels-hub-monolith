package com.carrentalservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {

    private String make;

    private String model;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    private int yearOfProduction;

    private String color;

    private int mileage;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double amount;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch = new Branch();

    private String urlOfImage;

}
