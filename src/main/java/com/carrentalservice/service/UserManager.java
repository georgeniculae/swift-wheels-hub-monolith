package com.carrentalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManager {

    private final UserService userService;

    @Autowired
    public UserManager(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public void createUsers() {
        userService.createUsersIfThereAreNotAny();
    }

}
