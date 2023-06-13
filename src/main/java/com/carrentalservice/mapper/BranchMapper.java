package com.carrentalservice.mapper;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BranchMapper {

    BranchDto mapEntityToDto(Branch branch);

    Branch mapDtoToEntity(BranchDto branchDto);

}
