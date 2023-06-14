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
public class Branch extends BaseEntity {

    private String name;

    private String address;

    @OneToMany(mappedBy = "workingBranch", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    @ManyToOne(fetch = FetchType.EAGER)
    private RentalOffice rentalOffice;

    @OneToMany(mappedBy = "rentalBranch", cascade = CascadeType.ALL)
    private List<Booking> rentalBookings = new ArrayList<>();

    @OneToMany(mappedBy = "returnBranch", cascade = CascadeType.ALL)
    private List<Booking> returnBookings = new ArrayList<>();

}
