package com.sda.carrentalservice.dto;

public class BaseEntityDTO {

    protected Long id;

    public BaseEntityDTO(Long id) {
        this.id = id;
    }

    public BaseEntityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
