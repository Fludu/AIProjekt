package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;

public record CarAssistanceDTO(
        String name,
        double price) {
    public static CarAssistanceDTO of(CarAssistance carAssistance) {
        return new CarAssistanceDTO(
                carAssistance.getName(),
                carAssistance.getPrice()
        );
    }
}
