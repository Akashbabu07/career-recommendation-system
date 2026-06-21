package com.example.App.service.auth.impl;

import com.example.App.config.JwtUtil;
import com.example.App.dto.request.LoginRequest;
import com.example.App.dto.request.RefreshTokenRequest;
import com.example.App.dto.request.RegisterRequest;
import com.example.App.dto.response.AuthResponse;
import com.example.App.entity.Role;
import com.example.App.entity.User;
import com.example.App.exception.DuplicateEmailException;
import com.example.App.exception.InvalidCredentialsException;
import com.example.App.exception.InvalidTokenException;
import com.example.App.exception.UserNotFoundException;
import com.example.App.repository.UserRepository;
import com.example.App.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {

        if(userRepository.findByEmail(
                request.getEmail()).isPresent()) {

            throw new DuplicateEmailException(
                    "Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setCgpa(request.getCgpa());
        user.setSkills(request.getSkills());
        user.setInterests(request.getInterests());
        user.setProjects(request.getProjects());

        user.setRole(Role.USER);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid Credentials");
        }

        String accessToken =
                jwtUtil.generateAccessToken(
                        user.getEmail(),
                        user.getRole().name());

        String refreshToken =
                jwtUtil.generateRefreshToken(
                        user.getEmail());

        user.setRefreshToken(refreshToken);

        userRepository.save(user);

        return new AuthResponse(
                accessToken,
                refreshToken);
    }

    @Override
    public AuthResponse refreshToken(
            RefreshTokenRequest request) {

        User user = userRepository
                .findByRefreshToken(
                        request.getRefreshToken())
                .orElseThrow(() ->
                        new InvalidTokenException(
                                "Invalid Refresh Token"));

        String accessToken =
                jwtUtil.generateAccessToken(
                        user.getEmail(),
                        user.getRole().name());

        return new AuthResponse(
                accessToken,
                request.getRefreshToken());
    }

    @Override
    public String logout(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));

        user.setRefreshToken(null);

        userRepository.save(user);

        return "Logged Out Successfully";
    }
}