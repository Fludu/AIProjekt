package com.example.aiprojekt.Exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedProcessException extends RuntimeException {
    public UnauthorizedProcessException(){
        super("Unauthorized process exception " + HttpStatus.UNAUTHORIZED);
    }
}
