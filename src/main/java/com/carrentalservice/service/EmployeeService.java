package com.carrentalservice.service;

import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchService branchService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BranchService branchService) {
        this.employeeRepository = employeeRepository;
        this.branchService = branchService;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }

        throw new NotFoundException("Employee with id " + id + " does not exist.");
    }

    public Employee updateEmployee(Employee newEmployee) {
        Employee existingEmployee = findEmployeeById(newEmployee.getId());
        newEmployee.setId(existingEmployee.getId());

        return saveEmployee(newEmployee);
    }

    public List<Employee> getEmployeesInBranch(Long id) {
        Branch branch = branchService.findBranchById(id);

        return branch.getEmployees();
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public Employee findEmployeeByName(String searchString) {
        return employeeRepository.findEmployeeByName(searchString);
    }

}
