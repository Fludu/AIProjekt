package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Position;
import lombok.Getter;

@Getter
public class PositionRequest {
    String name;

    public static Position of(PositionRequest positionRequest) {
        return new Position(positionRequest.getName());
    }
}
