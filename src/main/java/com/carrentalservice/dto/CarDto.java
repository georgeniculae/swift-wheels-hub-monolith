package com.carrentalservice.dto;

import com.carrentalservice.entity.BodyType;
import com.carrentalservice.entity.Status;
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
    private Status status;
    private Double amount;
    private BranchDto branch;
    private String urlOfImage;

}
