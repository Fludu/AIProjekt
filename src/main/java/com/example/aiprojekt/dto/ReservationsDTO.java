package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record ReservationsDTO(
        String date,
        List<CarAssistanceInfoDTO> carAssistances
) {
    public static ReservationsDTO of(Reservation reservation) {
        List<CarAssistanceInfoDTO> carAssistanceInfoDTOS = new ArrayList<>();
        if (reservation.getCarAssistances() != null) {
            for (CarAssistance carAssistance : reservation.getCarAssistances()) {
                CarAssistanceInfoDTO carAssistanceInfoDTO = CarAssistanceInfoDTO.of(carAssistance);
                carAssistanceInfoDTOS.add(carAssistanceInfoDTO);
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = reservation.getDate().format(formatter);
        return new ReservationsDTO(formattedDateTime, carAssistanceInfoDTOS);
    }
}