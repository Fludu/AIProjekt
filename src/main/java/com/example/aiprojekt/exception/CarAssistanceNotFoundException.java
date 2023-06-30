package com.example.aiprojekt.exception;

public class CarAssistanceNotFoundException extends RuntimeException {
    public CarAssistanceNotFoundException(String id) {
        super("Car assistance with id " + id + " not found");
    }
}
