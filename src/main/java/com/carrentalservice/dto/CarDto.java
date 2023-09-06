package com.carrentalservice.dto;

import com.carrentalservice.entity.BodyType;
import com.carrentalservice.entity.CarStatus;
import lombok.*;

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
    private Long branchId;
    private String urlOfImage;

}
