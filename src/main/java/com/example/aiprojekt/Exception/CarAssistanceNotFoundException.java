package com.example.aiprojekt.Exception;

public class CarAssistanceNotFoundException extends RuntimeException {
    public CarAssistanceNotFoundException(String id) {
        super("Car assistance with id " + id + " not found");
    }
}
