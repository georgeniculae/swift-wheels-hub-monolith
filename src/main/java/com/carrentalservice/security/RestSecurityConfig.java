package com.carrentalservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Order(2)
public class RestSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsConfig userDetailsConfig;

    @Bean
    public SecurityFilterChain restFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**");

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requestMatcherRegistry) -> requestMatcherRegistry
                        .requestMatchers("/api/authentication/**").permitAll()
                        .requestMatchers("/api/branch/**", "/api/car/**", "/api/customer/**", "/api/employee/**", "/api/rental-office/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(loginConfig -> loginConfig.loginPage("/login").permitAll())
                .logout(logoutConfig -> logoutConfig.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsConfig.userDetailsService());
        authProvider.setPasswordEncoder(userDetailsConfig.passwordEncoder());

        return authProvider;
    }

}
