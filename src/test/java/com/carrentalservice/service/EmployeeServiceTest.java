package com.carrentalservice.service;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.EmployeeMapper;
import com.carrentalservice.mapper.EmployeeMapperImpl;
import com.carrentalservice.repository.EmployeeRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

        AssertionUtils.assertEmployee(employee, employeeDtoList.get(0));
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

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> employeeService.findEmployeeById(1L));

        assertNotNull(notFoundException);
        assertEquals("Employee with id 1 does not exist", notFoundException.getMessage());
    }

    @Test
    void updateEmployeeTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);
        EmployeeDto employeeDto = TestUtils.getResourceAsJson("/data/EmployeeDto.json", EmployeeDto.class);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        assertDoesNotThrow(() -> employeeService.updateEmployee(employeeDto));
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto);
        AssertionUtils.assertEmployee(employee, updatedEmployeeDto);
    }

    @Test
    void findEmployeesByBranchIdTest_success() {
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);
        branch.setEmployees(List.of(employee));

        when(branchService.findEntityById(anyLong())).thenReturn(branch);

        assertDoesNotThrow(() -> employeeService.findEmployeesByBranchId(1L));
        List<EmployeeDto> employeeDtoList = employeeService.findEmployeesByBranchId(1L);
        AssertionUtils.assertEmployee(employee, employeeDtoList.get(0));
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

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> employeeService.findEmployeeByFilter("Test"));

        assertNotNull(notFoundException);
        assertEquals("Employee with filter: Test does not exist", notFoundException.getMessage());
    }

}
