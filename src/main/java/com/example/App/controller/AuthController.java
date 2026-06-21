package com.example.App.controller;

import com.example.App.dto.request.LoginRequest;
import com.example.App.dto.request.RefreshTokenRequest;
import com.example.App.dto.request.RegisterRequest;
import com.example.App.dto.response.AuthResponse;
import com.example.App.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(
            @Valid @RequestBody RefreshTokenRequest request) {

        return authService.refreshToken(request);
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication) {

        String email = authentication.getName();

        return authService.logout(email);
    }
}