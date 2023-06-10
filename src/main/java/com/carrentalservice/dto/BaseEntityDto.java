package com.carrentalservice.dto;

public class BaseEntityDto {

    protected Long id;

    public BaseEntityDto(Long id) {
        this.id = id;
    }

    public BaseEntityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
