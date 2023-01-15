package com.example.aiprojekt.Exception;

public class NameBusyException extends RuntimeException {
    public NameBusyException(String name) {
        super("Name is busy " + name);
    }
}
