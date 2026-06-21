package com.example.App.service;

import com.example.App.config.JwtUtil;
import com.example.App.dto.request.LoginRequest;
import com.example.App.dto.response.AuthResponse;
import com.example.App.entity.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.App.repository.UserRepository;
import com.example.App.service.auth.impl.AuthServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    void testLogin_success() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("hashedPassword");

        LoginRequest request = new LoginRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("1234");


        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(any(), any()))
                .thenReturn(true);


        when(jwtUtil.generateAccessToken(any(), any()))
                .thenReturn("access-token");

        when(jwtUtil.generateRefreshToken(any()))
                .thenReturn("refresh-token");

        AuthResponse response =
                authService.login(request);


        assertNotNull(response);
        assertEquals("access-token", response.getAccessToken());
    }

}
