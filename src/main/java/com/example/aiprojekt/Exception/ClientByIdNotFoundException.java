package com.example.aiprojekt.Exception;

public class ClientByIdNotFoundException extends RuntimeException {
    public ClientByIdNotFoundException(String id) {
        super("Could not find reservation with id " + id);
    }
}
