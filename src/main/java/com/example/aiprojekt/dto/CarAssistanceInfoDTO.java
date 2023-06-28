package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;

public record CarAssistanceInfoDTO(
        String name,
        double price) {
    public static CarAssistanceInfoDTO of(CarAssistance carAssistance) {
        return new CarAssistanceInfoDTO(
                carAssistance.getName(),
                carAssistance.getPrice()
        );
    }
}
