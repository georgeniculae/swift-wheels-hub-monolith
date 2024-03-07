package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BranchDto;
import com.swiftwheelshub.dto.RentalOfficeDetails;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.RentalOffice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BranchMapper {

    @Mapping(target = "rentalOfficeDetails", expression = "java(mapToRentalOfficeDetails(branch.getRentalOffice()))")
    BranchDto mapEntityToDto(Branch branch);

    Branch mapDtoToEntity(BranchDto branchDto);

    RentalOfficeDetails mapToRentalOfficeDetails(RentalOffice rentalOffice);

}
