package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.mapper.CustomerMapper;
import com.carrentalservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customer")
@CrossOrigin(origins = "*")
public class CustomerRestController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.findCustomerById(id);

        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = customerService.saveCustomer(customerDto);

        return ResponseEntity.ok(savedCustomerDto);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = customerService.updateCustomer(customerDto);

        return ResponseEntity.ok(updatedCustomerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomer() {
        List<CustomerDto> customerDtoList = customerService.findAllCustomer();

        return ResponseEntity.ok(customerDtoList);
    }

    @GetMapping(path = "/username")
    public ResponseEntity<Boolean> existsUserByUsername(@RequestParam("username") String username) {
        boolean existsUser = customerService.existsByUsername(username);

        return ResponseEntity.ok(existsUser);
    }

}
