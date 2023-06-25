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

    @PostMapping(path = "/customer")
    public ResponseEntity<UserDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto registeredCustomerDto = userService.registerCustomer(customerDto);

        return ResponseEntity.ok(registeredCustomerDto);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        Long users = userService.countUsers();

        return ResponseEntity.ok(users);
    }

}
