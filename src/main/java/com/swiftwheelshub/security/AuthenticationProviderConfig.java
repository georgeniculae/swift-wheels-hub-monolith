package com.swiftwheelshub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;

@Configuration
@RequiredArgsConstructor
public class AuthenticationProviderConfig {

    private final NimbusJwtDecoder nimbusJwtDecoder;
    private final Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationTokenConverter;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                BearerTokenAuthenticationToken bearer = (BearerTokenAuthenticationToken) authentication;
                Jwt jwt = nimbusJwtDecoder.decode(bearer.getToken());

                return jwtAuthenticationTokenConverter.convert(jwt);
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
            }
        };
    }

}
