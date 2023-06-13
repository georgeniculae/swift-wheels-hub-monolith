package com.carrentalservice.mapper;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {

    EmployeeDto mapEntityToDto(Employee employee);

    Employee mapDtoToEntity(EmployeeDto employeeDto);

}
