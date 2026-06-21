package com.example.App.service.ml;

import com.example.App.client.MLServiceClient;
import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MLService {

    private final MLServiceClient mlServiceClient;

    public PredictionResponse getPrediction(
            PredictionRequest request) {

        return mlServiceClient.predict(
                request
        );
    }
}