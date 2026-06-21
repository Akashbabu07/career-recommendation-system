package com.example.App.exception;

import com.example.App.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            UserNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleUserNotFound(
            UserNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                404,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(
            DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse>
    handleDuplicateEmail(
            DuplicateEmailException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                409,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(
            InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse>
    handleInvalidCredentials(
            InvalidCredentialsException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                401,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(
            InvalidTokenException.class)
    public ResponseEntity<ErrorResponse>
    handleInvalidToken(
            InvalidTokenException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                403,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>
    handleValidation(
            MethodArgumentNotValidException ex) {

        String error =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(
                        new ErrorResponse(
                                error,
                                400,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(
            ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse>
    handleConstraintViolation(
            ConstraintViolationException ex) {

        return ResponseEntity
                .badRequest()
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                400,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGeneralException(
            Exception ex) {

        log.error(
                "Unexpected Error",
                ex
        );

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        new ErrorResponse(
                                "Internal Server Error",
                                500,
                                LocalDateTime.now()
                        )
                );
    }
}