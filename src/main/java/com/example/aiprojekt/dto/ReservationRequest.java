package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class ReservationRequest {
    @NotBlank
    LocalDateTime localDateTime;
    @NotBlank
    String clientId;
    public static Reservation of(ReservationRequest reservationRequest, Client client){
        return new Reservation(reservationRequest.getLocalDateTime(),client);
    }
}
