package com.example.aiprojekt.Exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(String name) {
        super("Could not find position with name " + name);
    }

}