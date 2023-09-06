package com.carrentalservice.service;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.EmployeeMapper;
import com.carrentalservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchService branchService;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = employeeMapper.mapDtoToEntity(employeeDto);

        Branch workingBranch = branchService.findEntityById(employeeDto.getWorkingBranchId());
        newEmployee.setWorkingBranch(workingBranch);
        Employee savedEmployee = employeeRepository.save(newEmployee);

        return employeeMapper.mapEntityToDto(savedEmployee);
    }

    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::mapEntityToDto)
                .toList();
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto findEmployeeById(Long id) {
        Employee employee = findEntityById(id);

        return employeeMapper.mapEntityToDto(employee);
    }

    public Employee findEntityById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }

        throw new NotFoundException("Employee with id " + id + " does not exist");
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployeeDto) {
        Long actualId = getId(id, updatedEmployeeDto.getId());

        Employee existingEmployee = findEntityById(actualId);

        Branch workingBranch = branchService.findEntityById(updatedEmployeeDto.getWorkingBranchId());

        existingEmployee.setFirstName(updatedEmployeeDto.getFirstName());
        existingEmployee.setLastName(updatedEmployeeDto.getLastName());
        existingEmployee.setJobPosition(updatedEmployeeDto.getJobPosition());
        existingEmployee.setWorkingBranch(workingBranch);

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.mapEntityToDto(savedEmployee);
    }

    public List<EmployeeDto> findEmployeesByBranchId(Long id) {
        return employeeRepository.findByBranchId(id)
                .stream()
                .map(employeeMapper::mapEntityToDto)
                .toList();
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public EmployeeDto findEmployeeByFilter(String searchString) {
        Optional<Employee> optionalEmployee = employeeRepository.findByFilter(searchString);

        if (optionalEmployee.isPresent()) {
            return employeeMapper.mapEntityToDto(optionalEmployee.get());
        }

        throw new NotFoundException("Employee with filter: " + searchString + " does not exist");
    }

    private Long getId(Long id, Long updatedEmployeeId) {
        Long actualId = updatedEmployeeId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
