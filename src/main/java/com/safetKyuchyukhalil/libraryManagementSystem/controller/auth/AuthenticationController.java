package com.safetKyuchyukhalil.libraryManagementSystem.controller.auth;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication.AuthenticationRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication.AuthenticationResponse;
import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication.RegisterRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.service.auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
