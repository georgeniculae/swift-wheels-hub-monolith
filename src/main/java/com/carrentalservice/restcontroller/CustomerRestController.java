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
        CustomerDto customerDTO = customerTransformer.transformFromEntityToDTO(customer);
        return ResponseEntity.ok(customerDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDTO) {
        Customer customer = customerTransformer.transformFromDTOToEntity(customerDTO);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDto savedCustomerDTO = customerTransformer.transformFromEntityToDTO(saveCustomer);
        return ResponseEntity.ok(savedCustomerDTO);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDTO) {
        Customer customer = customerTransformer.transformFromDTOToEntity(customerDTO);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDto savedCustomerDTO = customerTransformer.transformFromEntityToDTO(saveCustomer);
        return ResponseEntity.ok(savedCustomerDTO);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomer(){
        List<Customer> allCustomer = customerService.findAllCustomer();
        List<CustomerDto> allCustomerDTO = new ArrayList<>();

        for (Customer customer: allCustomer){
            allCustomerDTO.add(customerTransformer.transformFromEntityToDTO(customer));
        }
        return ResponseEntity.ok(allCustomerDTO);
    }
}
