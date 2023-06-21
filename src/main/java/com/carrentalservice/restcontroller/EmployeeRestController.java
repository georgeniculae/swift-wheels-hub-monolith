package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.EmployeeDto;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        EmployeeDto employeeDto = employeeService.findEmployeeById(id);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto);

        return ResponseEntity.ok(updatedEmployeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.findAllEmployees();

        return ResponseEntity.ok(employeeDtoList);
    }

    @GetMapping(path = "/branch/{id}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByBranchId(@RequestParam("id") Long id) {
        List<EmployeeDto> employeeDtoList = employeeService.findEmployeesByBranchId(id);

        return ResponseEntity.ok(employeeDtoList);
    }

}
