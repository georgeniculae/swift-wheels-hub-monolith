package com.carrentalservice.service;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.EmployeeMapper;
import com.carrentalservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
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

        Branch workingBranch = branchService.findEntityById(employeeDto.getWorkingBranch().getId());
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

    private Employee findEntityById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }

        throw new NotFoundException("Employee with id " + id + " does not exist");
    }

    public EmployeeDto updateEmployee(EmployeeDto newEmployeeDto) {
        Employee newEmployee = employeeMapper.mapDtoToEntity(newEmployeeDto);

        Employee existingEmployee = findEntityById(newEmployeeDto.getId());
        Branch workingBranch = branchService.findEntityById(newEmployee.getWorkingBranch().getId());

        existingEmployee.setFirstName(newEmployee.getFirstName());
        existingEmployee.setLastName(newEmployee.getLastName());
        existingEmployee.setJobPosition(newEmployee.getJobPosition());
        existingEmployee.setWorkingBranch(workingBranch);

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.mapEntityToDto(savedEmployee);
    }

    public List<EmployeeDto> getEmployeesInBranch(Long id) {
        Branch branch = branchService.findEntityById(id);

        return branch.getEmployees()
                .stream()
                .map(employeeMapper::mapEntityToDto)
                .toList();
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public EmployeeDto findEmployeeByName(String searchString) {
        Employee employee = employeeRepository.findEmployeeByName(searchString);

        return employeeMapper.mapEntityToDto(employee);
    }

}
