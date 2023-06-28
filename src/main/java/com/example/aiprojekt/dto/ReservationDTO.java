package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private LocalDateTime localDateTime;
    private Client client;
    private List<CarAssistanceDTO> carAssistances;

    public static ReservationDTO of(Reservation reservation) {
        List<CarAssistanceDTO> carAssistancesDTO = new ArrayList<>();
        if(reservation.getCarAssistances()!=null) {
            for (CarAssistance carAssistance : reservation.getCarAssistances()) {
                carAssistancesDTO.add(CarAssistanceDTO.of(carAssistance));
            }
        }
        return new ReservationDTO(
                reservation.getLocalDateTime(),
                reservation.getClient(),
                carAssistancesDTO
        );
    }
}