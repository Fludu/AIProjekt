package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarService;

import java.util.List;
import java.util.stream.Collectors;

public record CarServiceDTO(
   String name,
   List<ReservationsDTO> reservations
) {
    public static CarServiceDTO of(CarService carService) {
        List<ReservationsDTO> reservationsDTOs = carService.getReservations().stream()
                .map(ReservationsDTO::of)
                .collect(Collectors.toList());

        return new CarServiceDTO(
                carService.getName(),
                reservationsDTOs
        );
    }
}