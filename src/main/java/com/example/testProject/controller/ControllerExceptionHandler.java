package com.example.testProject.controller;

import com.example.testProject.exception.CustomEntityNotFoundException;
import com.example.testProject.exception.EntityNotBelongException;
import com.example.testProject.exception.PhoneAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomEntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(CustomEntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EntityNotBelongException.class)
    public ResponseEntity<String> handleEntityNotBelongException(EntityNotBelongException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        response.put("errors", errors);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response.toString());
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<String> handlePhoneAlreadyExistsException(PhoneAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

}
