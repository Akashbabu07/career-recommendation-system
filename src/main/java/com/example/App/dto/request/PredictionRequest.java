package com.example.App.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PredictionRequest {

    @NotBlank(message = "Skills are required")
    private String skills;
}