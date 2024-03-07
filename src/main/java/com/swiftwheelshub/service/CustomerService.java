package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.CustomerMapper;
import com.swiftwheelshub.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String CUSTOMER = "customer";
    private static final String SUPPORT = "support";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.mapDtoToEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.mapEntityToDto(savedCustomer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findCustomersWithoutBaseUsers(ADMIN, USER, CUSTOMER, SUPPORT)
                .stream()
                .map(customerMapper::mapEntityToDto)
                .toList();
    }

    public CustomerResponse findCustomerById(Long id) {
        Customer customer = findEntityById(id);

        return customerMapper.mapEntityToDto(customer);
    }

    public Customer findEntityById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Customer with id " + id + " does not exist"));
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest updatedCustomerRequest) {
        Long actualId = getId(id, updatedCustomerRequest.getId());

        Customer existingCustomer = findEntityById(actualId);

        existingCustomer.setFirstName(updatedCustomerRequest.getFirstName());
        existingCustomer.setLastName(updatedCustomerRequest.getLastName());
        existingCustomer.setEmail(updatedCustomerRequest.getEmail());
        existingCustomer.setAddress(updatedCustomerRequest.getAddress());

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.mapEntityToDto(savedCustomer);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public Long countCustomersWithoutBaseUsers() {
        return customerRepository.countCustomersWithoutBaseUsers(ADMIN, USER, SUPPORT);
    }

    public CustomerResponse findCustomerByFilter(String filter) {
        return customerRepository.findByFilter(filter)
                .map(customerMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Customer with filter: " + filter + " does not exist"));
    }

    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }

    public CustomerResponse findLoggedInCustomer() {
        return customerMapper.mapEntityToDto(getLoggedInCustomer());
    }

    public Customer getLoggedInCustomer() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return findEntityByUsername(name);
    }

    private Long getId(Long id, Long updatedCustomerId) {
        Long actualId = updatedCustomerId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

    private Customer findEntityByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Customer with username " + username + " does not exist"));
    }

}
