package com.thoughtworks.capability.gtb.restfulapidesign.Exceptions;

public class StudentNotExistsException extends RuntimeException {
    public StudentNotExistsException(String message) {
        super(message);
    }
}
