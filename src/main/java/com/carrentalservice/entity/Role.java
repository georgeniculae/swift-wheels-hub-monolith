package com.carrentalservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_SUPPORT("ROLE_SUPPORT"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

    private final String name;

}
