package com.swiftwheelshub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String USERNAME_CLAIM = "preferred_username";
    private final Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(source, extractGrantedAuthorities(source), extractUsername(source));
    }

    public String extractUsername(Jwt source) {
        return (String) source.getClaims().get(USERNAME_CLAIM);
    }

    public Collection<GrantedAuthority> extractGrantedAuthorities(Jwt source) {
        return jwtGrantedAuthoritiesConverter.convert(source);
    }

}
