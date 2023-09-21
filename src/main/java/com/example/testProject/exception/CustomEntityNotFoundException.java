package com.example.testProject.exception;

import jakarta.persistence.EntityNotFoundException;

public class CustomEntityNotFoundException extends EntityNotFoundException {

    public CustomEntityNotFoundException(Long id, String entityName) {
        super(entityName + " with id " + id + " not found");
    }
}