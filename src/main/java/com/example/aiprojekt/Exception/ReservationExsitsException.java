package com.example.aiprojekt.Exception;

import java.util.Date;

public class ReservationExsitsException extends RuntimeException{
    public ReservationExsitsException(Date localDateTime, String clientId){
        super("Reservation on this with this date "+localDateTime+" "+"and clientId " + clientId+" exists");
    }
}