package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.CarAssistance;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class CarAssistanceRequest {
    @NotBlank String name;
    @NotBlank double price;


    public static CarAssistance of(CarAssistanceRequest request) {
        return new CarAssistance(request.getName(), request.getPrice());
    }
}


