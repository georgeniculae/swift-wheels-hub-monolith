package com.swiftwheelshub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkUri;
    private final JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter;
    private final AuthenticationManager authenticationManager;
    private final KeycloakLogoutHandler keycloakLogoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/",
                                        "/register",
                                        "/user/register",
                                        "/css/**",
                                        "/images/**").permitAll()
                                .requestMatchers("/branch/**",
                                        "/car/**",
                                        "/customer/**",
                                        "/employee/**",
                                        "/rental-office/**").authenticated()
                                .anyRequest().authenticated()
                )
                .formLogin(loginConfig -> loginConfig.loginPage("/login").permitAll())
                .logout(logoutConfig -> logoutConfig.invalidateHttpSession(true)
                        .addLogoutHandler(keycloakLogoutHandler)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll())
                .oauth2ResourceServer(resourceServerSpec ->
                        resourceServerSpec.jwt(jwtSpec -> jwtSpec.jwkSetUri(jwkUri)
                                .authenticationManager(authenticationManager)
                                .jwtAuthenticationConverter(jwtAuthenticationTokenConverter)))
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
