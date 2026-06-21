package com.example.App.exception;

public class DuplicateEmailException
        extends RuntimeException {

    public DuplicateEmailException(
            String message) {

        super(message);
    }
}