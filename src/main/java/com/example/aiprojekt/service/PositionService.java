package com.example.aiprojekt.service;

import com.example.aiprojekt.dto.PositionRequest;
import com.example.aiprojekt.models.Position;
import com.example.aiprojekt.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;


    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }


    public Position savePosition(PositionRequest positionRequest) {
        return positionRepository.save(PositionRequest.of(positionRequest));
    }
}
