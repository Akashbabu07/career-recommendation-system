package com.example.App.exception;

public class InvalidTokenException
        extends RuntimeException {

    public InvalidTokenException(
            String message) {

        super(message);
    }
}