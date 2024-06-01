package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BranchDetails;
import com.swiftwheelshub.dto.EmployeeRequest;
import com.swiftwheelshub.dto.EmployeeResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface EmployeeMapper {

    @Mapping(target = "workingBranchDetails", expression = "java(mapToBranchDetails(employee.getWorkingBranch()))")
    EmployeeResponse mapEntityToDto(Employee employee);

    Employee mapDtoToEntity(EmployeeRequest employeeRequest);

    BranchDetails mapToBranchDetails(Branch branch);

}
