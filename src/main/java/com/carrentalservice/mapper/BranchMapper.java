package com.carrentalservice.mapper;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BranchMapper {

    @Mapping(target = "rentalOfficeId", expression = "java(branch.getRentalOffice().getId())")
    BranchDto mapEntityToDto(Branch branch);

    Branch mapDtoToEntity(BranchDto branchDto);

}
