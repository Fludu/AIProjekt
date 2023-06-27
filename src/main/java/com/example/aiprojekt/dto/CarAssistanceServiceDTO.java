package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;

import java.util.List;
import java.util.stream.Collectors;

public record CarAssistanceServiceDTO(
   String name,
   List<ReservationsDTO> reservations
) {
    public static CarAssistanceServiceDTO of(CarAssistance carAssistance) {
        List<ReservationsDTO> reservationsDTOs = carAssistance.getReservations().stream()
                .map(ReservationsDTO::of)
                .collect(Collectors.toList());

        return new CarAssistanceServiceDTO(
                carAssistance.getName(),
                reservationsDTOs
        );
    }
}