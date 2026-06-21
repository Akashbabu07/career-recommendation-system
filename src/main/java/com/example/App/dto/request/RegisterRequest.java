package com.example.App.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6,
            message = "Password must contain at least 6 characters")
    private String password;

    @Min(value = 0,
            message = "CGPA cannot be less than 0")
    @Max(value = 10,
            message = "CGPA cannot be greater than 10")
    private double cgpa;

    @NotEmpty(message = "Skills cannot be empty")
    private List<String> skills;

    private List<String> interests;

    private List<String> projects;
}