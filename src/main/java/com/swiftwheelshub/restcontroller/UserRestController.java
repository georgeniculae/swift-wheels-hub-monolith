package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.dto.UserDto;
import com.swiftwheelshub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserRestController {

    private final UserService userService;

    @GetMapping(path = "/username")
    public ResponseEntity<Boolean> existsUserByUsername(@RequestParam("username") String username) {
        boolean existsUser = userService.existsUserByUsername(username);

        return ResponseEntity.ok(existsUser);
    }

    @PostMapping(path = "/customer")
    public ResponseEntity<UserDto> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse registeredCustomerRequest = userService.registerCustomer(customerRequest);

        return ResponseEntity.ok(registeredCustomerRequest);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        Long users = userService.countUsers();

        return ResponseEntity.ok(users);
    }

}
