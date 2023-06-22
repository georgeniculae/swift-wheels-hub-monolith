package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.dto.UserDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.entity.Role;
import com.carrentalservice.entity.User;
import com.carrentalservice.mapper.CustomerMapper;
import com.carrentalservice.mapper.UserMapper;
import com.carrentalservice.repository.UserRepository;
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
    private final UserMapper userMapper;

    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserDto saveUser(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        User savedUser = userRepository.save(user);

        return userMapper.mapEntityToDto(savedUser);
    }

    public CustomerDto registerCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setUsername(customerDto.getUsername());
        customer.setPassword(encoder.encode(customerDto.getPassword()));
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setRole(Role.ROLE_CUSTOMER);

        Customer savedCustomer = userRepository.save(customer);

        return customerMapper.mapEntityToDto(savedCustomer);
    }

    public Long countUsers() {
        return userRepository.count();
    }

    public void saveEntity(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
