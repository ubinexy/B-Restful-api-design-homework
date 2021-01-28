package com.thoughtworks.capability.gtb.restfulapidesign.Exceptions;

public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String message) {
        super(message);
    }
}
