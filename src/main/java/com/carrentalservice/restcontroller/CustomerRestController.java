package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.service.CustomerService;
import com.carrentalservice.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/customer")
@CrossOrigin(origins = "*")
public class CustomerRestController {

    private final CustomerService customerService;
    private final CustomerTransformer customerTransformer;

    @Autowired
    public CustomerRestController(CustomerService customerService, CustomerTransformer customerTransformer) {
        this.customerService = customerService;
        this.customerTransformer = customerTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerDto customerDto = customerTransformer.transformFromEntityToDto(customer);

        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerTransformer.transformFromDtoToEntity(customerDto);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDto savedCustomerDto = customerTransformer.transformFromEntityToDto(saveCustomer);

        return ResponseEntity.ok(savedCustomerDto);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerTransformer.transformFromDtoToEntity(customerDto);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDto savedCustomerDto = customerTransformer.transformFromEntityToDto(saveCustomer);

        return ResponseEntity.ok(savedCustomerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomer(){
        List<Customer> allCustomer = customerService.findAllCustomer();
        List<CustomerDto> allCustomerDto = new ArrayList<>();

        for (Customer customer: allCustomer){
            allCustomerDto.add(customerTransformer.transformFromEntityToDto(customer));
        }

        return ResponseEntity.ok(allCustomerDto);
    }
}
