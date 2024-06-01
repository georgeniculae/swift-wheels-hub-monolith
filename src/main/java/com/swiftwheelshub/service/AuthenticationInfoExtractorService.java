package com.swiftwheelshub.service;

import com.swiftwheelshub.security.JwtAuthenticationTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationInfoExtractorService {

    private final JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter;

    public String getUsername() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return jwtAuthenticationTokenConverter.extractUsername(jwt);
    }

}
