package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.dto.UserDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.entity.User;
import com.carrentalservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final CustomerService customerService;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository, CustomerService customerService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.customerService = customerService;
    }

    public void createUsersIfThereAreNotAny() {
        if (countUsers() == 0) {
            createUsers();
        }
    }

    private void createUsers() {
        List<User> users = new ArrayList<>();

        if (!userRepository.existsByUsername("admin")) {
            users.add(new Customer("admin", encoder.encode("admin"), "ROLE_ADMIN"));
        }

        if (!userRepository.existsByUsername("user")) {
            users.add(new Customer("user", encoder.encode("user"), "ROLE_USER"));
        }

        if (!userRepository.existsByUsername("support")) {
            users.add(new Customer("support", encoder.encode("support"), "ROLE_SUPPORT"));
        }

        if (!customerService.existsByUsername("customer")) {
            users.add(new Customer("customer", encoder.encode("customer"), "ROLE_CUSTOMER"));
        }

        userRepository.saveAll(users);
    }


    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUserDto(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    public void registerCustomer(CustomerDto customerDto) {
        Customer user = new Customer();

        user.setUsername(customerDto.getUsername());
        user.setPassword(encoder.encode(customerDto.getPassword()));
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setEmail(customerDto.getEmail());
        user.setAddress(customerDto.getAddress());
        user.setRole("ROLE_CUSTOMER");

        userRepository.save(user);
    }

    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    optionalUser.get().getUsername(),
                    optionalUser.get().getPassword(),
                    List.of(new SimpleGrantedAuthority(optionalUser.get().getRole()))
            );
        }

        throw new UsernameNotFoundException("Invalid username or password");
    }

}
