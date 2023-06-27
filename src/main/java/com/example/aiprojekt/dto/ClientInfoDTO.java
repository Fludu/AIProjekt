package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Client;

public record ClientInfoDTO(
        String id,
        String name,
        String city
) {
    public static ClientInfoDTO of(Client client) {
        return new ClientInfoDTO(
                client.getId(),
                client.getName(),
                client.getCity()

        );
    }


}
