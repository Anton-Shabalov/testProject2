package com.example.testProject.exception;

public class PhoneAlreadyExistsException extends RuntimeException {

    public PhoneAlreadyExistsException(String phoneNumber) {
        super("Phone number " + phoneNumber + " already exists in the system");
    }
}