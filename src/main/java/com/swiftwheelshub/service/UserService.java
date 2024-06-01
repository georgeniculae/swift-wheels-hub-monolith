package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.entity.Role;
import com.swiftwheelshub.entity.User;
import com.swiftwheelshub.mapper.CustomerMapper;
import com.swiftwheelshub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;

    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();

        customer.setUsername(customerRequest.getUsername());
        customer.setPassword(encoder.encode(customerRequest.getPassword()));
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setAddress(customerRequest.getAddress());
        customer.setRole(Role.ROLE_CUSTOMER);

        Customer savedCustomer = userRepository.save(customer);

        return customerMapper.mapEntityToDto(savedCustomer);
    }

    public Long countUsers() {
        return userRepository.count();
    }

    public void saveEntity(Customer customer) {
        userRepository.save(customer);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
