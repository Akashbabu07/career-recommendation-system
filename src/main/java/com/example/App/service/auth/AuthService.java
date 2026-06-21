package com.example.App.service.auth;

import com.example.App.dto.request.LoginRequest;
import com.example.App.dto.request.RefreshTokenRequest;
import com.example.App.dto.request.RegisterRequest;
import com.example.App.dto.response.AuthResponse;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(
            RefreshTokenRequest request);

    String logout(String email);
}