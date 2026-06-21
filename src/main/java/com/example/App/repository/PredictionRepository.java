package com.example.App.repository;

import com.example.App.entity.Prediction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository
        extends MongoRepository<Prediction, String> {

    List<Prediction> findByEmail(
            String email);

}