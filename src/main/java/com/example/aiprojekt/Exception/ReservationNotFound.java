package com.example.aiprojekt.Exception;


public class ReservationNotFound extends RuntimeException {
    public ReservationNotFound(String id) {
        super("Could not find reservation with id " + id);
    }

}