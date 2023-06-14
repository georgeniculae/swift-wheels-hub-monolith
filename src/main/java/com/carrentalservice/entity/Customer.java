package com.carrentalservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<>();

    public Customer(String username, String password, String role) {
        super(username, password, role);
    }

}
