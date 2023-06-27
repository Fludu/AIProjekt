package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarSAssistance;

import java.util.List;
import java.util.stream.Collectors;

public record CarAssistanceServiceDTO(
   String name,
   List<ReservationsDTO> reservations
) {
    public static CarAssistanceServiceDTO of(CarSAssistance carSAssistance) {
        List<ReservationsDTO> reservationsDTOs = carSAssistance.getReservations().stream()
                .map(ReservationsDTO::of)
                .collect(Collectors.toList());

        return new CarAssistanceServiceDTO(
                carSAssistance.getName(),
                reservationsDTOs
        );
    }
}