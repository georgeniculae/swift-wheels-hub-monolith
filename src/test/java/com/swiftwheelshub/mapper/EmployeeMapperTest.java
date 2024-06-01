package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.EmployeeRequest;
import com.swiftwheelshub.dto.EmployeeResponse;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
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

        EmployeeResponse employeeResponse = employeeMapper.mapEntityToDto(employee);

        assertNotNull(employeeResponse);
        AssertionUtils.assertEmployeeResponse(employee, employeeResponse);
    }

    @Test
    void mapDtoToEntityTest_success() {
        EmployeeRequest employeeRequest = TestUtils.getResourceAsJson("/data/EmployeeRequest.json", EmployeeRequest.class);

        Employee employee = employeeMapper.mapDtoToEntity(employeeRequest);

        assertNotNull(employeeRequest);
        AssertionUtils.assertEmployeeRequest(employee, employeeRequest);
    }

}
