package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BodyType;
import com.swiftwheelshub.entity.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {

    private Long id;
    private String make;
    private String model;
    private BodyType bodyType;
    private int yearOfProduction;
    private String color;
    private int mileage;
    private CarStatus carStatus;
    private BigDecimal amount;
    private BranchDto branch;
    private String urlOfImage;

}
