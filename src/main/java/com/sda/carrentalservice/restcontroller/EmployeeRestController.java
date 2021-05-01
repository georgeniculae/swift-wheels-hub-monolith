package com.sda.carrentalservice.restcontroller;

import com.sda.carrentalservice.dto.EmployeeDTO;
import com.sda.carrentalservice.entity.Employee;
import com.sda.carrentalservice.service.EmployeeService;
import com.sda.carrentalservice.transformer.EmployeeTransformer;
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
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDTO employeeDTO = employeeTransformer.transformFromEntityToDTO(employee);
        return ResponseEntity.ok(employeeDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeTransformer.transformFromDTOToEntity(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDTO savedEmployeeDTO = employeeTransformer.transformFromEntityToDTO(savedEmployee);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeTransformer.transformFromDTOToEntity(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDTO savedEmployeeDTO = employeeTransformer.transformFromEntityToDTO(savedEmployee);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listAllEmployees() {
        List<Employee> allEmployees = employeeService.findAllEmployees();
        List<EmployeeDTO> allEmployeesDTO = new ArrayList<>();

        for (Employee employee : allEmployees) {
            allEmployeesDTO.add(employeeTransformer.transformFromEntityToDTO(employee));
        }
        return ResponseEntity.ok(allEmployeesDTO);
    }
}
