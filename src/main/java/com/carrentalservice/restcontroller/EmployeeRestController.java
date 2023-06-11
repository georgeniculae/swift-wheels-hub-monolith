package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.service.EmployeeService;
import com.carrentalservice.mapper.EmployeeMapper;
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
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDto employeeDto = employeeMapper.mapFromEntityToDto(employee);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapFromDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto savedEmployeeDTO = employeeMapper.mapFromEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapFromDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.updateEmployee(employee);
        EmployeeDto savedEmployeeDto = employeeMapper.mapFromEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.findAllEmployees()
                .stream()
                .map(employeeMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(employeeDtoList);
    }

}
