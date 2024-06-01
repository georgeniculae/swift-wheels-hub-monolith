package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.BranchResponse;
import com.swiftwheelshub.dto.RentalOfficeDetails;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.RentalOffice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BranchMapper {

    @Mapping(target = "rentalOfficeDetails", expression = "java(mapToRentalOfficeDetails(branch.getRentalOffice()))")
    BranchResponse mapEntityToDto(Branch branch);

    Branch mapDtoToEntity(BranchRequest branchRequest);

    RentalOfficeDetails mapToRentalOfficeDetails(RentalOffice rentalOffice);

}
