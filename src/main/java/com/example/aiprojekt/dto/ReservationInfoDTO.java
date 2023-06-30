package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ReservationInfoDTO {
    private String date;
    private Client client;
    private List<String> carAssistances;

    public static ReservationInfoDTO of(Reservation reservation) {
        List<String> carAssistancesDTO = new ArrayList<>();
        if(reservation.getCarAssistances()!=null) {
            for (CarAssistance carAssistance : reservation.getCarAssistances()) {
                carAssistancesDTO.add(carAssistance.getName());
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = reservation.getDate().format(formatter);
        return new ReservationInfoDTO(
                formattedDateTime,
                reservation.getClient(),
                carAssistancesDTO
        );
    }
}