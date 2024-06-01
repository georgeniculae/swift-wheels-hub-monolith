package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.EmployeeRequest;
import com.swiftwheelshub.dto.EmployeeResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.EmployeeMapper;
import com.swiftwheelshub.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchService branchService;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee newEmployee = employeeMapper.mapDtoToEntity(employeeRequest);

        Branch workingBranch = branchService.findEntityById(employeeRequest.getWorkingBranchDetails().getId());
        newEmployee.setWorkingBranch(workingBranch);
        Employee savedEmployee = employeeRepository.save(newEmployee);

        return employeeMapper.mapEntityToDto(savedEmployee);
    }

    public List<EmployeeResponse> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::mapEntityToDto)
                .toList();
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeResponse findEmployeeById(Long id) {
        Employee employee = findEntityById(id);

        return employeeMapper.mapEntityToDto(employee);
    }

    public Employee findEntityById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Employee with id " + id + " does not exist"));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest updatedEmployeeRequest) {
        Employee existingEmployee = findEntityById(id);

        Branch workingBranch = branchService.findEntityById(updatedEmployeeRequest.getWorkingBranchDetails().getId());

        existingEmployee.setFirstName(updatedEmployeeRequest.getFirstName());
        existingEmployee.setLastName(updatedEmployeeRequest.getLastName());
        existingEmployee.setJobPosition(updatedEmployeeRequest.getJobPosition());
        existingEmployee.setWorkingBranch(workingBranch);

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.mapEntityToDto(savedEmployee);
    }

    public List<EmployeeResponse> findEmployeesByBranchId(Long id) {
        return employeeRepository.findByBranchId(id)
                .stream()
                .map(employeeMapper::mapEntityToDto)
                .toList();
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public EmployeeResponse findEmployeeByFilter(String searchString) {
        return employeeRepository.findByFilter(searchString)
                .map(employeeMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Employee with filter: " + searchString + " does not exist"));
    }

    private Long getId(Long id, Long updatedEmployeeId) {
        Long actualId = updatedEmployeeId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
