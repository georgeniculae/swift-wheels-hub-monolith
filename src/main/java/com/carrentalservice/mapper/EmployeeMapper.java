package com.carrentalservice.mapper;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDto mapEntityToDto(Employee employee) {
        EmployeeDto employeeDTO = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDTO);

        return employeeDTO;
    }

    public Employee mapDtoToEntity(EmployeeDto employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        return employee;
    }

}
