package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record ClientDTO (
   String name,

   String lastName,

   String email,
   String city
){

    public static ClientDTO of(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getLastName(),
                client.getName(),
                client.getCity()

        );
    }

}
