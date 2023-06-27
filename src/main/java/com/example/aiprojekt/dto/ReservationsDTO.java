package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ReservationsDTO(
        LocalDateTime localDateTime,
        List<CarAssistanceDTO> carAssistances
) {
    public static ReservationsDTO of(Reservation reservation) {
        List<CarAssistanceDTO> carAssistanceDTOs = new ArrayList<>();
        if (reservation.getCarAssistances() != null) {
            for (CarAssistance carAssistance : reservation.getCarAssistances()) {
                CarAssistanceDTO carAssistanceDTO = CarAssistanceDTO.of(carAssistance);
                carAssistanceDTOs.add(carAssistanceDTO);
            }
        }
        return new ReservationsDTO(reservation.getLocalDateTime(), carAssistanceDTOs);
    }
}