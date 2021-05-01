package com.sda.carrentalservice.service;

import com.sda.carrentalservice.dto.CustomerDTO;
import com.sda.carrentalservice.dto.UserDTO;
import com.sda.carrentalservice.entity.Customer;
import com.sda.carrentalservice.entity.User;
import com.sda.carrentalservice.repository.CustomerRepository;
import com.sda.carrentalservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public void createUsers() {
        List<User> users = new ArrayList<>();
        if (!userRepository.findByUsername("admin").isPresent()) {
            users.add(new Customer("admin", encoder.encode("admin"), "ROLE_ADMIN"));
        }
        if (!userRepository.findByUsername("user").isPresent()) {
            users.add(new Customer("user", encoder.encode("user"), "ROLE_USER"));
        }
        if (!userRepository.findByUsername("support").isPresent()) {
            users.add(new Customer("support", encoder.encode("support"), "ROLE_SUPPORT"));
        }
        if (!customerRepository.findCustomerByUsername("customer").isPresent()) {
            users.add(new Customer("customer", encoder.encode("customer"), "ROLE_CUSTOMER"));
        }
        userRepository.saveAll(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    optionalUser.get().getUsername(),
                    optionalUser.get().getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(optionalUser.get().getRole())));
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User saveUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer user = new Customer();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(encoder.encode(customerDTO.getPassword()));
        user.setFirstName(customerDTO.getFirstName());
        user.setLastName(customerDTO.getLastName());
        user.setEmail(customerDTO.getEmail());
        user.setAddress(customerDTO.getAddress());
        user.setRole("ROLE_CUSTOMER");
        return userRepository.save(user);
    }

    public Long userCount() {
        return userRepository.count();
    }
}
