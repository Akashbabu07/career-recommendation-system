package com.example.App.service;

import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import com.example.App.entity.Prediction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.App.repository.PredictionRepository;
import com.example.App.service.ml.MLService;
import com.example.App.service.prediction.impl.PredictionServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PredictionServiceTest {

    @Mock

    private PredictionRepository predictionRepository;

    @Mock
    private MLService mlService;


    @InjectMocks
    private PredictionServiceImpl predictionService;

    @Test
    void  testPredict_success(){
        PredictionRequest request = new PredictionRequest();
        request.setSkills("Java, Spring Boot");

        PredictionResponse mlResponse = new PredictionResponse();
        mlResponse.setPrediction("Backend Engineer");

        when(mlService.getPrediction(request))
                .thenReturn(mlResponse);
        when(predictionRepository.save(any(Prediction.class)))
                .thenAnswer(i -> i.getArgument(0));
        PredictionResponse result =
                predictionService.predict(request, "test@gmail.com");

        assertEquals("Backend Engineer", result.getPrediction());
    }
}
