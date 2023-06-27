package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.CarAssistanceDTO;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.service.CarAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car-assistances")
public class CarAssistanceController {
    private final CarAssistanceService carAssistanceService;

    @Autowired
    public CarAssistanceController(CarAssistanceService carAssistanceService) {
        this.carAssistanceService = carAssistanceService;
    }

    @GetMapping
    public ResponseEntity<List<CarAssistanceDTO>> getAllCarAssistances() {
        List<CarAssistanceDTO> carAssistances = carAssistanceService.getAllCarServices();
        return ResponseEntity.ok(carAssistances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarAssistanceDTO> getCarAssistanceById(@PathVariable String id) {
        return carAssistanceService.getCarServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarAssistanceDTO> createCarAssistance(@RequestBody CarAssistance carAssistance) {
        CarAssistanceDTO createdCarAssistance = carAssistanceService.createCarService(carAssistance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarAssistance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarAssistance> updateCarAssistance(@PathVariable String id, @RequestBody CarAssistance carAssistance) {
        return carAssistanceService.getCarServiceById(id)
                .map(existingCarAssistance -> {
                    CarAssistance updatedCarAssistance = carAssistanceService.updateCarService(id, carAssistance);
                    return ResponseEntity.ok(updatedCarAssistance);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarAssistanceDTO> deleteCarAssistance(@PathVariable String id) {
        return ResponseEntity.of(carAssistanceService.getCarServiceById(id));
    }
}