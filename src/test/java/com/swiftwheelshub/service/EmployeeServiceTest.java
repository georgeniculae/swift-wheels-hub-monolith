package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.EmployeeDto;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.EmployeeMapper;
import com.swiftwheelshub.mapper.EmployeeMapperImpl;
import com.swiftwheelshub.repository.EmployeeRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BranchService branchService;

    @Spy
    private EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @Test
    void saveEmployeeTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);
        EmployeeDto employeeDto = TestUtils.getResourceAsJson("/data/EmployeeDto.json", EmployeeDto.class);
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(branchService.findEntityById(anyLong())).thenReturn(branch);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        assertDoesNotThrow(() -> employeeService.saveEmployee(employeeDto));
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);

        AssertionUtils.assertEmployee(employee, savedEmployeeDto);

        verify(employeeMapper, times(2)).mapEntityToDto(any(Employee.class));
    }

    @Test
    void findAllEmployeesTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);

        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        assertDoesNotThrow(() -> employeeService.findAllEmployees());
        List<EmployeeDto> employeeDtoList = employeeService.findAllEmployees();

        AssertionUtils.assertEmployee(employee, employeeDtoList.getFirst());
    }

    @Test
    void findEmployeeByIdTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertDoesNotThrow(() -> employeeService.findEmployeeById(1L));
        EmployeeDto employeeDto = employeeService.findEmployeeById(1L);

        AssertionUtils.assertEmployee(employee, employeeDto);
    }

    @Test
    void findEmployeeByIdTest_errorOnFindingById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> employeeService.findEmployeeById(1L));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Employee with id 1 does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

    @Test
    void updateEmployeeTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);
        EmployeeDto employeeDto = TestUtils.getResourceAsJson("/data/EmployeeDto.json", EmployeeDto.class);
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(branchService.findEntityById(anyLong())).thenReturn(branch);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto updatedEmployeeDto = assertDoesNotThrow(() -> employeeService.updateEmployee(1L, employeeDto));
        AssertionUtils.assertEmployee(employee, updatedEmployeeDto);
    }

    @Test
    void findEmployeesByBranchIdTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);

        when(employeeRepository.findByBranchId(anyLong())).thenReturn(List.of(employee));

        List<EmployeeDto> employeeDtoList = assertDoesNotThrow(() -> employeeService.findEmployeesByBranchId(1L));
        AssertionUtils.assertEmployee(employee, employeeDtoList.getFirst());
    }

    @Test
    void findEmployeeByFilterTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);

        when(employeeRepository.findByFilter(anyString())).thenReturn(Optional.of(employee));

        assertDoesNotThrow(() -> employeeService.findEmployeeByFilter("Ion"));
        EmployeeDto employeeDto = employeeService.findEmployeeByFilter("Ion");

        AssertionUtils.assertEmployee(employee, employeeDto);
    }

    @Test
    void findEmployeeByFilterTest_errorOnFindingByFilter() {
        when(employeeRepository.findByFilter(anyString())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> employeeService.findEmployeeByFilter("Test"));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Employee with filter: Test does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
