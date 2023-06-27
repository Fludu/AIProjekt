package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Reservations;

import java.util.List;
import java.util.stream.Collectors;

public record ReservationsDTO(
   String name,
   String secondName,
   String email,
   double salary,
   List<CarServiceDTO> carServices
) {
    public static ReservationsDTO of(Reservations reservations) {
        List<CarServiceDTO> carServiceDTOs = reservations.getCarServices().stream()
                .map(CarServiceDTO::of)
                .collect(Collectors.toList());

        return new ReservationsDTO(
                reservations.getName(),
                reservations.getSecondName(),
                reservations.getEmail(),
                reservations.getSalary(),
                carServiceDTOs
        );
    }
}