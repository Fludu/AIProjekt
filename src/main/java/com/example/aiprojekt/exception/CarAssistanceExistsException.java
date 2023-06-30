package com.example.aiprojekt.exception;

public class CarAssistanceExistsException extends RuntimeException {
    public CarAssistanceExistsException(String name) {
        super("Car assistance with that name " + name + " exists");
    }
}
