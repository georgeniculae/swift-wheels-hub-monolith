package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.CustomerMapper;
import com.carrentalservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String CUSTOMER = "customer";
    private static final String SUPPORT = "support";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapDtoToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.mapEntityToDto(savedCustomer);
    }

    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findCustomersWithoutBaseUsers(ADMIN, USER, CUSTOMER, SUPPORT)
                .stream()
                .map(customerMapper::mapEntityToDto)
                .toList();
    }

    public CustomerDto findCustomerById(Long id) {
        Customer customer = findEntityById(id);

        return customerMapper.mapEntityToDto(customer);
    }

    public Customer findEntityById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }

        throw new NotFoundException("Customer with id " + id + " does not exist");
    }

    public CustomerDto updateCustomer(CustomerDto updatedCustomerDto) {
        Customer existingCustomer = findEntityById(updatedCustomerDto.getId());

        existingCustomer.setFirstName(updatedCustomerDto.getFirstName());
        existingCustomer.setLastName(updatedCustomerDto.getLastName());
        existingCustomer.setEmail(updatedCustomerDto.getEmail());
        existingCustomer.setAddress(updatedCustomerDto.getAddress());

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.mapEntityToDto(savedCustomer);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public Long countCustomersWithoutBaseUsers() {
        return customerRepository.countCustomersWithoutBaseUsers(ADMIN, USER, SUPPORT);
    }

    public CustomerDto findCustomerByFilter(String searchString) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByFilter(searchString);

        if (optionalCustomer.isPresent()) {
            return customerMapper.mapEntityToDto(optionalCustomer.get());
        }

        throw new NotFoundException("Customer with filter: " + searchString + " does not exist");
    }

    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }

    public CustomerDto getLoggedInCustomerDto() {
        return customerMapper.mapEntityToDto(getLoggedInCustomer());
    }

    public Customer getLoggedInCustomer() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return findEntityByUsername(name);
    }

    private Customer findEntityByUsername(String username) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }

        throw new NotFoundException("Customer with username " + username + " does not exist");
    }

}
