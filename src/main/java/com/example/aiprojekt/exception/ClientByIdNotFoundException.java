package com.example.aiprojekt.exception;

public class ClientByIdNotFoundException extends RuntimeException {
    public ClientByIdNotFoundException(String id) {
        super("Could not find reservation with id " + id);
    }
}
