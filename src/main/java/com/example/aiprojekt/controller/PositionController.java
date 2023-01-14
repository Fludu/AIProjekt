package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.CompanyRequest;
import com.example.aiprojekt.dto.PositionRequest;
import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Position;
import com.example.aiprojekt.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;


    @PostMapping
    public ResponseEntity<Position> createPosition(@Valid @RequestBody PositionRequest positionRequest) {
        Position position = positionService.savePosition(positionRequest);
        return ResponseEntity.ok(position);
    }

    @GetMapping
    public ResponseEntity<List<Position>> getAllPositions() {
        List<Position> positions = positionService.getAllPositions();
        return ResponseEntity.ok(positions);
    }
}
