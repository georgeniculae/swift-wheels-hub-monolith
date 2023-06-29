package com.carrentalservice.mapper;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperTest {

    private final EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);

        EmployeeDto employeeDto = employeeMapper.mapEntityToDto(employee);

        assertNotNull(employeeDto);
        AssertionUtils.assertEmployee(employee, employeeDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        EmployeeDto employeeDto = TestUtils.getResourceAsJson("/data/EmployeeDto.json", EmployeeDto.class);

        Employee employee = employeeMapper.mapDtoToEntity(employeeDto);

        assertNotNull(employeeDto);
        AssertionUtils.assertEmployee(employee, employeeDto);
    }

}
