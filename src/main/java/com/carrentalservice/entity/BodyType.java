package com.carrentalservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BodyType {

    CHOOSE("Choose"),
    HATCHBACK("Hatchback"),
    SEDAN("Sedan"),
    SUV("SUV"),
    COUPE("Coupe"),
    CONVERTIBLE("Convertible"),
    WAGON("Wagon"),
    VAN("Van"),
    JEEP("Jeep");

    private final String displayName;

}
