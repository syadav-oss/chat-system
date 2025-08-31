package org.example.controller;

import org.example.domain.UserAuthEntity;
import org.example.service.UserAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;

    public UserAuthController(UserAuthService userAuthService, PasswordEncoder passwordEncoder) {
        this.userAuthService = userAuthService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAuthEntity userAuthEntity) {
        userAuthEntity.setPassword(passwordEncoder.encode(userAuthEntity.getPassword()));

        userAuthService.save(userAuthEntity);
        return ResponseEntity.ok("User registered successfully");
    }
}
