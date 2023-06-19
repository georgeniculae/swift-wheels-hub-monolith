package com.carrentalservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarStatus {

    CHOOSE("Choose"),
    NOT_AVAILABLE("Not available"),
    BROKEN("Broken"),
    IN_REPAIR("In repair"),
    IN_SERVICE("In service"),
    AVAILABLE("Available");

    private final String displayName;

}
