package com.carrentalservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("Admin"),
    USER("User"),
    SUPPORT("Support"),
    CUSTOMER("Customer");

    private final String name;

}
