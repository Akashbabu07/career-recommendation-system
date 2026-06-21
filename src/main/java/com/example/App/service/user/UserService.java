package com.example.App.service.user;

import com.example.App.dto.response.UserResponse;

public interface UserService {

    UserResponse getProfile(
            String email);
}