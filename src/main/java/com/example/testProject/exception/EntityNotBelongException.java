package com.example.testProject.exception;

import jakarta.persistence.EntityNotFoundException;

public class EntityNotBelongException extends EntityNotFoundException {

    public EntityNotBelongException(String entityParrent, String entityInner) {
        super(entityInner + " do not belong " + entityParrent);
    }
}