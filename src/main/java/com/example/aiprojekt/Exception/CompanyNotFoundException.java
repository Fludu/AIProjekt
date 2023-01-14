package com.example.aiprojekt.Exception;


public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String id) {
        super("Could not find company with id " + id);
    }

}