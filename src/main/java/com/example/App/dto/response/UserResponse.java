package com.example.App.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String id;

    private String name;

    private String email;

    private double cgpa;

    private List<String> skills;

    private List<String> interests;

    private List<String> projects;
}