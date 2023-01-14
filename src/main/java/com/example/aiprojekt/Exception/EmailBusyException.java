package com.example.aiprojekt.Exception;

public class EmailBusyException extends RuntimeException {
    public EmailBusyException(String email) {
        super("Email is busy " + email);
    }
}
