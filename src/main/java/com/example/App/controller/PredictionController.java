package com.example.App.controller;

import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import com.example.App.entity.Prediction;
import com.example.App.service.prediction.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predictions")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping("/predict")
    public PredictionResponse predict(
            @Valid @RequestBody PredictionRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return predictionService.predict(
                request,
                email
        );
    }

    @GetMapping("/history")
    public List<Prediction> getHistory(
            Authentication authentication) {

        String email = authentication.getName();

        return predictionService.getHistory(email);
    }
}