package com.carrentalservice.service;

import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }

        throw new NotFoundException("Customer with id " + id + " does not exist.");
    }

    public Customer findCustomerByUsername(String username) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUsername(username);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }

        throw new NotFoundException("Customer with username " + username + " does not exist.");
    }

    public Customer updateCustomer(Customer newCustomer) {
        Customer existingCustomer = findCustomerById(newCustomer.getId());
        newCustomer.setId(existingCustomer.getId());

        return saveCustomer(newCustomer);
    }

    public void deleteCustomerById(Long id) {
        findCustomerById(id);

        customerRepository.deleteById(id);
    }

    public Long countCustomers() {
        return customerRepository.count();
    }

    public Customer findCustomerByName(String searchString) {
        return customerRepository.findCustomerByName(searchString);
    }

    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }

    public Customer getCustomerLoggedIn() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return findCustomerByUsername(name);
    }

}
