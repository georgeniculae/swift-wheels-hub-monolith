package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.CustomerDto;
import com.swiftwheelshub.mapper.CustomerMapper;
import com.swiftwheelshub.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = customerService.updateCustomer(id, customerDto);

        return ResponseEntity.ok(updatedCustomerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomer() {
        List<CustomerDto> customerDtoList = customerService.findAllCustomers();

        return ResponseEntity.ok(customerDtoList);
    }

    @GetMapping(path = "/username")
    public ResponseEntity<Boolean> existsUserByUsername(@RequestParam("username") String username) {
        boolean existsUser = customerService.existsByUsername(username);

        return ResponseEntity.ok(existsUser);
    }

}
