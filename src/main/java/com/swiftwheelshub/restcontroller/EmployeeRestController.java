package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.EmployeeRequest;
import com.swiftwheelshub.dto.EmployeeResponse;
import com.swiftwheelshub.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponse employeeResponse = employeeService.findEmployeeById(id);

        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countEmployees() {
        Long numberOfEmployees = employeeService.countEmployees();

        return ResponseEntity.ok(numberOfEmployees);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse savedEmployeeResponse = employeeService.saveEmployee(employeeRequest);

        return ResponseEntity.ok(savedEmployeeResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse updatedEmployeeResponse = employeeService.updateEmployee(id, employeeRequest);

        return ResponseEntity.ok(updatedEmployeeResponse);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> listAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.findAllEmployees();

        return ResponseEntity.ok(employeeResponses);
    }

    @GetMapping(path = "/branch/{id}")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesByBranchId(@PathVariable("id") Long id) {
        List<EmployeeResponse> employeeResponses = employeeService.findEmployeesByBranchId(id);

        return ResponseEntity.ok(employeeResponses);
    }

}
