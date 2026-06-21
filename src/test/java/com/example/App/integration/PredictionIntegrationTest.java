package com.example.App.integration;

import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.App.repository.PredictionRepository;
import com.example.App.service.ml.MLService;
import com.example.App.service.prediction.PredictionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PredictionIntegrationTest {

    @Autowired
    private
    PredictionService predictionService;

    @Autowired
    private PredictionRepository predictionRepository;

    @MockBean
    private MLService mlService;

    @Test
    void testPredictionFlow() {

        // 1. Fake ML response
        PredictionResponse mlResponse = new PredictionResponse();
        mlResponse.setPrediction("Backend Engineer");

        when(mlService.getPrediction(any()))
                .thenReturn(mlResponse);


        PredictionRequest request = new PredictionRequest();
        request.setSkills("Java, Spring Boot");

        PredictionResponse response =
                predictionService.predict(request, "test@gmail.com");

        assertEquals("Backend Engineer", response.getPrediction());


        assertFalse(
                predictionRepository.findByEmail("test@gmail.com")
                        .isEmpty()
        );
    }
}