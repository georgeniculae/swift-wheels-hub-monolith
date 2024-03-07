package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("id") Long id) {
        CustomerResponse customerResponse = customerService.findCustomerById(id);

        return ResponseEntity.ok(customerResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse savedCustomerResponse = customerService.saveCustomer(customerRequest);

        return ResponseEntity.ok(savedCustomerResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedCustomerResponse = customerService.updateCustomer(id, customerRequest);

        return ResponseEntity.ok(updatedCustomerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listAllCustomer() {
        List<CustomerResponse> customerResponses = customerService.findAllCustomers();

        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping(path = "/username")
    public ResponseEntity<Boolean> existsUserByUsername(@PathVariable("username") String username) {
        boolean existsUser = customerService.existsByUsername(username);

        return ResponseEntity.ok(existsUser);
    }

}
