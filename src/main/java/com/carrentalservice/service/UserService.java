package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.dto.UserDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.entity.User;
import com.carrentalservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUserDto(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRole(ROLE_USER);

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
        user.setRole(ROLE_CUSTOMER);

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
