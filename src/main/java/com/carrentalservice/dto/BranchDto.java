package com.carrentalservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BranchDto extends BaseEntityDto {

    private String name;
    private String address;
    private Long rentalOfficeId;

}
