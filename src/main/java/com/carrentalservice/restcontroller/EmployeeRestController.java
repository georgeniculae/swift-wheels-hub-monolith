package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.service.EmployeeService;
import com.carrentalservice.transformer.EmployeeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final EmployeeTransformer employeeTransformer;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, EmployeeTransformer employeeTransformer) {
        this.employeeService = employeeService;
        this.employeeTransformer = employeeTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDto employeeDto = employeeTransformer.transformFromEntityToDto(employee);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.transformFromDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto savedEmployeeDTO = employeeTransformer.transformFromEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.transformFromDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto savedEmployeeDto = employeeTransformer.transformFromEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listAllEmployees() {
        List<Employee> allEmployees = employeeService.findAllEmployees();
        List<EmployeeDto> allEmployeesDto = new ArrayList<>();

        for (Employee employee : allEmployees) {
            allEmployeesDto.add(employeeTransformer.transformFromEntityToDto(employee));
        }

        return ResponseEntity.ok(allEmployeesDto);
    }

}
