package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.mapper.EmployeeMapper;
import com.carrentalservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDto employeeDto = employeeMapper.mapEntityToDto(employee);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto savedEmployeeDTO = employeeMapper.mapEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapDtoToEntity(employeeDto);
        Employee updateEmployee = employeeService.updateEmployee(employee);
        EmployeeDto updatedEmployeeDto = employeeMapper.mapEntityToDto(updateEmployee);

        return ResponseEntity.ok(updatedEmployeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.findAllEmployees()
                .stream()
                .map(employeeMapper::mapEntityToDto)
                .toList();

        return ResponseEntity.ok(employeeDtoList);
    }

}
