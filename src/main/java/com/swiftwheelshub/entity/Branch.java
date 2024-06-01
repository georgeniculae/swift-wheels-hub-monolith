package com.swiftwheelshub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Branch extends BaseEntity {

    private String name;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalOffice rentalOffice;

}
