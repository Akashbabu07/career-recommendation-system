package com.example.App.client;

import com.example.App.dto.request.PredictionRequest;
import com.example.App.dto.response.PredictionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class MLServiceClient {

    private final RestTemplate restTemplate;

    @Value("${ml.base-url}")
    private String mlBaseUrl;

    public PredictionResponse predict(
            PredictionRequest request) {

        try {

            PredictionResponse response =
                    restTemplate.postForObject(
                            mlBaseUrl + "/predict",
                            request,
                            PredictionResponse.class
                    );

            if (response == null) {

                throw new RuntimeException(
                        "Empty response from ML Service"
                );
            }

            return response;

        } catch (ResourceAccessException ex) {

            log.error(
                    "ML Service Unavailable",
                    ex
            );

            throw new RuntimeException(
                    "ML Service is currently unavailable"
            );

        } catch (Exception ex) {

            log.error(
                    "Error calling ML Service",
                    ex
            );

            throw new RuntimeException(
                    "Prediction service failed"
            );
        }
    }
}