package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.service.CustomerService;
import com.carrentalservice.mapper.CustomerMapper;
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
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerRestController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerDto customerDto = customerMapper.mapFromEntityToDto(customer);

        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.mapFromDtoToEntity(customerDto);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDto savedCustomerDto = customerMapper.mapFromEntityToDto(saveCustomer);

        return ResponseEntity.ok(savedCustomerDto);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.mapFromDtoToEntity(customerDto);
        Customer saveCustomer = customerService.updateCustomer(customer);
        CustomerDto savedCustomerDto = customerMapper.mapFromEntityToDto(saveCustomer);

        return ResponseEntity.ok(savedCustomerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomer() {
        List<CustomerDto> customerDtoList = customerService.findAllCustomer()
                .stream()
                .map(customerMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(customerDtoList);
    }

}
