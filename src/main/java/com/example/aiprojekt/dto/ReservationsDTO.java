package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Reservations;

import java.util.List;
import java.util.stream.Collectors;

public record ReservationsDTO(
   String name,
   String secondName,
   String email,
   double salary,
   List<CarAssistanceServiceDTO> carServices
) {
    public static ReservationsDTO of(Reservations reservations) {
        List<CarAssistanceServiceDTO> carAssistanceServiceDTOS = reservations.getCarSAssistances().stream()
                .map(CarAssistanceServiceDTO::of)
                .collect(Collectors.toList());

        return new ReservationsDTO(
                reservations.getName(),
                reservations.getSecondName(),
                reservations.getEmail(),
                reservations.getSalary(),
                carAssistanceServiceDTOS
        );
    }
}