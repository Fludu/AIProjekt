package com.example.aiprojekt.exception;

public class EmailBusyException extends RuntimeException {
    public EmailBusyException(String email) {
        super("Email is busy " + email);
    }
}
