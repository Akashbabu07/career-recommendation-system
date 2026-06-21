package com.example.App.mapper;

import com.example.App.dto.request.RegisterRequest;
import com.example.App.dto.response.UserResponse;
import com.example.App.entity.Role;
import com.example.App.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setCgpa(request.getCgpa());
        user.setSkills(request.getSkills());
        user.setInterests(request.getInterests());
        user.setProjects(request.getProjects());

        user.setRole(Role.USER);

        return user;
    }

    public UserResponse toResponse(User user) {

        UserResponse response =
                new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        response.setCgpa(user.getCgpa());

        response.setSkills(user.getSkills());
        response.setInterests(user.getInterests());
        response.setProjects(user.getProjects());

        return response;
    }
}