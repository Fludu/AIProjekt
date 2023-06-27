package com.example.aiprojekt.dto;


import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public record ClientDTO(
        String name,
        String lastName,
        String email,
        String city,
        List<ReservationsDTO> listOfReservations
) {
    public static ClientDTO of(Client client) {
        return new ClientDTO(
                client.getName(),
                client.getLastName(),
                client.getEmail(),
                client.getCity(),
                fillListOfReservation(client)
        );
    }

    public static List<ReservationsDTO> fillListOfReservation(Client client) {
        List<ReservationsDTO> listOfReservations = new ArrayList<>();
        if (client.getReservations() != null) {
            for (Reservation reservation : client.getReservations()) {
                ReservationsDTO reservationsDTO = ReservationsDTO.of(reservation);
                listOfReservations.add(reservationsDTO);
            }
        }
        return listOfReservations;
    }

}