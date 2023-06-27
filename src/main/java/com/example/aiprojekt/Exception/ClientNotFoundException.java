package com.example.aiprojekt.Exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String email) {
        super("Could not find reservation with email " + email);
    }
}
