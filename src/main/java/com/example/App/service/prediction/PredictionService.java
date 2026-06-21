package com.example.App.service.prediction;

import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import com.example.App.entity.Prediction;

import java.util.List;

public interface PredictionService {

    PredictionResponse predict(
            PredictionRequest request,
            String email);

    List<Prediction> getHistory(
            String email);
}