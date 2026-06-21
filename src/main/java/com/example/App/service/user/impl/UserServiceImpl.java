package com.example.App.service.user.impl;

import com.example.App.dto.response.UserResponse;
import com.example.App.entity.User;
import com.example.App.exception.UserNotFoundException;
import com.example.App.mapper.UserMapper;
import com.example.App.repository.UserRepository;
import com.example.App.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl
        implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse getProfile(
            String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));

        return userMapper.toResponse(user);
    }
}