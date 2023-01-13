package com.example.aiprojekt.service;

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

    public void deleteEmployeeById(String id) {
        positionRepository.deleteById(id);
    }

    public Position saveEmployee(Position employee) {
        return positionRepository.save(employee);
    }

    public void updateEmployee(Position employee) {
        positionRepository.save(employee);
    }
}
