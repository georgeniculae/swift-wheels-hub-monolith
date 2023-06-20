package com.carrentalservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookingStatus {

    IN_PROGRESS("In progress"),
    CLOSED("Closed");

    private final String displayName;

}
