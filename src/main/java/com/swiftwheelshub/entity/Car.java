package com.swiftwheelshub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car extends BaseEntity {

    private String make;

    private String model;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    private Integer yearOfProduction;

    private String color;

    private Integer mileage;

    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_branch_id")
    private Branch originalBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actual_branch_id")
    private Branch actualBranch;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE)
    private byte[] image;

}
