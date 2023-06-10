package com.carrentalservice.config;

import com.carrentalservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManagerConfig {

    private final UserService userService;

    @Autowired
    public UserManagerConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public void createUsers() {
        userService.createUsersIfThereAreNotAny();
    }

}
