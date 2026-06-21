package com.example.App.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.App.dto.response.UserResponse;
import com.example.App.mapper.UserMapper;
import com.example.App.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.App.repository.UserRepository;
import com.example.App.service.user.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;


    @Mock
    private UserMapper userMapper;


    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void testGetProfile_success() {

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setName("Test User");


        UserResponse response = new UserResponse();
        response.setEmail("test@gmail.com");


        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        when(userMapper.toResponse(user))
                .thenReturn(response);

        UserResponse result =
                userService.getProfile("test@gmail.com");


        assertEquals("test@gmail.com", result.getEmail());
    }
}