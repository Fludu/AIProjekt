package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class ReservationRequest {
    @NotBlank
    LocalDateTime date;
    @NotBlank
    String clientId;

    List<String> carAssistanceId;

    public static Reservation of(ReservationRequest reservationRequest, Client client, List<CarAssistance> carAssistances) {
        return new Reservation(reservationRequest.getDate(), client, carAssistances);
    }

}
