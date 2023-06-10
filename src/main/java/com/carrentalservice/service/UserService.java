package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.dto.UserDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.entity.User;
import com.carrentalservice.repository.CustomerRepository;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public void createUsersIfThereAreNotAny() {
        if (countUsers() == 0) {
            createUsers();
        }
    }

    private void createUsers() {
        List<User> users = new ArrayList<>();

        if (userRepository.findByUsername("admin").isEmpty()) {
            users.add(new Customer("admin", encoder.encode("admin"), "ROLE_ADMIN"));
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            users.add(new Customer("user", encoder.encode("user"), "ROLE_USER"));
        }

        if (userRepository.findByUsername("support").isEmpty()) {
            users.add(new Customer("support", encoder.encode("support"), "ROLE_SUPPORT"));
        }

        if (customerRepository.findCustomerByUsername("customer").isEmpty()) {
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
                    List.of(new SimpleGrantedAuthority(optionalUser.get().getRole()))
            );
        }

        throw new UsernameNotFoundException("Invalid username or password");
    }

    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User saveUserDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public Customer registerCustomer(CustomerDto customerDto) {
        Customer user = new Customer();
        user.setUsername(customerDto.getUsername());
        user.setPassword(encoder.encode(customerDto.getPassword()));
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setEmail(customerDto.getEmail());
        user.setAddress(customerDto.getAddress());
        user.setRole("ROLE_CUSTOMER");

        return userRepository.save(user);
    }

    public Long countUsers() {
        return userRepository.count();
    }
}
