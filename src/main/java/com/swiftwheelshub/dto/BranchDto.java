package com.swiftwheelshub.dto;

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
public class BranchDto extends BaseEntityDto {

    private String name;
    private String address;
    private RentalOfficeDto rentalOffice;

}
