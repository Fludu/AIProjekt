package com.example.aiprojekt.exception;


public class ReservationNotFound extends RuntimeException {
    public ReservationNotFound(String id) {
        super("Could not find reservation with id " + id);
    }

}