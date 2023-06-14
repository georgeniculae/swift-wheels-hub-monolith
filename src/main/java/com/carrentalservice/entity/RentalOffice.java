package com.carrentalservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RentalOffice extends BaseEntity {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Internet domain cannot be empty")
    private String internetDomain;

    @NotEmpty(message = "Contact address domain cannot be empty")
    private String contactAddress;

    @NotEmpty(message = "Owner cannot be empty")
    private String owner;

    private String logoType;

    @OneToMany(mappedBy = "rentalOffice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Branch> branches;

}
