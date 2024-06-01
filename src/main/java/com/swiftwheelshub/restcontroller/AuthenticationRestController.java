package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.AuthenticationResponse;
import com.swiftwheelshub.dto.AuthenticationRequest;
import com.swiftwheelshub.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthenticationRestController {

//    private final AuthenticationService authenticationService;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }

}
