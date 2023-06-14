package com.carrentalservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RentalOfficeDto extends BaseEntityDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Internet domain cannot be empty")
    private String internetDomain;

    @NotEmpty(message = "Contact address cannot be empty")
    private String contactAddress;

    @NotEmpty(message = "Owner cannot be empty")
    private String owner;

    private String logoType;

    private List<BranchDto> branches;

}
