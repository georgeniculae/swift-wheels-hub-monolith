package com.sda.carrentalservice.service;

import com.sda.carrentalservice.entity.Booking;
import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.entity.Customer;
import com.sda.carrentalservice.exception.NotFoundException;
import com.sda.carrentalservice.repository.CustomerRepository;
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
        } else {
            throw new NotFoundException("Customer with id " + id + " does not exist.");
        }
    }

    public Customer findCustomerByUsername(String username) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUsername(username);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new NotFoundException("Customer with username " + username + " does not exist.");
        }
    }

    public void deleteCustomerById(Long id) {
        this.findCustomerById(id);
        customerRepository.deleteById(id);
    }

    public Long countCustomers() {
        return customerRepository.count();
    }

    public Customer findCustomerByName(String searchString) {
        return customerRepository.findCustomerByName(searchString);
    }

    public Customer getCustomerLoggedIn() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return findCustomerByUsername(name);
    }

}
