package com.sda.carrentalservice.service;

import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.entity.Employee;
import com.sda.carrentalservice.exception.NotFoundException;
import com.sda.carrentalservice.repository.BranchRepository;
import com.sda.carrentalservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
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
            Employee employee = optionalEmployee.get();
            return employee;
        } else {
            throw new NotFoundException("Employee with id " + id + " does not exist.");
        }
    }

    public List<Employee> getEmployeesInBranch(Long id) {
        return branchRepository.findById(id).get().getEmployees();
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public Employee findEmployeeByName(String searchString) {
        return employeeRepository.findEmployeeByName(searchString);
    }
}
