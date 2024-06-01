package com.swiftwheelshub.security;

import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.entity.Role;
import com.swiftwheelshub.entity.User;
import com.swiftwheelshub.dto.AuthenticationRequest;
import com.swiftwheelshub.dto.AuthenticationResponse;
import com.swiftwheelshub.dto.RegisterRequest;
import com.swiftwheelshub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Customer customer = new Customer();

        customer.setUsername(request.getUsername());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setRole(Role.ROLE_CUSTOMER);
        customer.setDateOfBirth(request.getDateOfBirth());

        userService.saveEntity(customer);

        var jwt = jwtService.generateToken(customer);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

}
