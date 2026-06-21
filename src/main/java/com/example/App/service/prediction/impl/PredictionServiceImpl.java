package com.example.App.service.prediction.impl;

import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import com.example.App.entity.Prediction;
import com.example.App.repository.PredictionRepository;
import com.example.App.service.ml.MLService;
import com.example.App.service.prediction.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl
        implements PredictionService {

    private final PredictionRepository predictionRepository;
    private final MLService mlService;

    @Override
    public PredictionResponse predict(
            PredictionRequest request,
            String email) {

        PredictionResponse response =
                mlService.getPrediction(request);

        Prediction prediction = new Prediction();

        prediction.setEmail(email);
        prediction.setPrediction(
                response.getPrediction());

        prediction.setSkillsUsed(
                request.getSkills());

        prediction.setTimestamp(
                LocalDateTime.now());

        predictionRepository.save(
                prediction);

        return response;
    }

    @Override
    public List<Prediction> getHistory(
            String email) {

        return predictionRepository
                .findByEmail(email);
    }
}