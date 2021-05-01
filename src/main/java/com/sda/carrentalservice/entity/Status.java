package com.sda.carrentalservice.entity;

public enum Status {
    CHOOSE("Choose"),
    NOT_AVAILABLE("Not available"),
    BROKEN("Broken"),
    IN_REPAIR("In repair"),
    IN_SERVICE("In service"),
    AVAILABLE("Available");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
