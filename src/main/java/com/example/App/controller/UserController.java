package com.example.App.controller;

import com.example.App.dto.response.UserResponse;
import com.example.App.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserResponse getProfile(
            Authentication authentication) {
        if (authentication == null) {
            throw new RuntimeException("No authentication found");
        }
        String email = authentication.getName();

        return userService.getProfile(email);
    }
}