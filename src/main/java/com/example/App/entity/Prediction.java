package com.example.App.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "predictions")
public class Prediction {

    @Id
    private String id;

    private String email;

    private String skillsUsed;

    private String prediction;

    private LocalDateTime timestamp;
}