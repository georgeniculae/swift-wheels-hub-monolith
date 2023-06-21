package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.dto.UserDto;
import com.carrentalservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService userService;

    @GetMapping(path = "/username")
    public ResponseEntity<Boolean> existsUserByUsername(@RequestParam("username") String username) {
        boolean existsUser = userService.existsUserByUsername(username);

        return ResponseEntity.ok(existsUser);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.saveUser(userDto);

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping(path = "customer")
    public ResponseEntity<UserDto> saveUser(@RequestBody CustomerDto customerDto) {
        CustomerDto registeredCustomerDto = userService.registerCustomer(customerDto);

        return ResponseEntity.ok(registeredCustomerDto);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        Long users = userService.countUsers();

        return ResponseEntity.ok(users);
    }

}
