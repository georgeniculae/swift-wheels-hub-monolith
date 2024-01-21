package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BodyType;
import com.swiftwheelshub.entity.CarStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CarDto extends BaseEntityDto {

    private String make;
    private String model;
    private BodyType bodyType;
    private int yearOfProduction;
    private String color;
    private int mileage;
    private CarStatus carStatus;
    private Double amount;
    private BranchDto branch;
    private String urlOfImage;

}
