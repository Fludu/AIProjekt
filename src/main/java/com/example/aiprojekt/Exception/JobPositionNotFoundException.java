package com.example.aiprojekt.Exception;

public class JobPositionNotFoundException extends RuntimeException {
    public JobPositionNotFoundException(String id) {
        super("Could not find job position with id " + id);
    }

}