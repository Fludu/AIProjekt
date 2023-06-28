package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.CarAssistanceInfoDTO;
import com.example.aiprojekt.dto.CarAssistanceRequest;
import com.example.aiprojekt.repository.CarAssistanceRepository;
import com.example.aiprojekt.service.CarAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-assistances")
public class CarAssistanceController {
    private final CarAssistanceService carAssistanceService;
    private final CarAssistanceRepository carAssistanceRepository;

    @Autowired
    public CarAssistanceController(CarAssistanceService carAssistanceService,
                                   CarAssistanceRepository carAssistanceRepository) {
        this.carAssistanceService = carAssistanceService;
        this.carAssistanceRepository = carAssistanceRepository;
    }

    @GetMapping
    public ResponseEntity<List<CarAssistanceInfoDTO>> getAllCarAssistances() {
        List<CarAssistanceInfoDTO> carAssistances = carAssistanceService.getAllCarServices();
        return ResponseEntity.ok(carAssistances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarAssistanceInfoDTO> getCarAssistanceById(@PathVariable String id) {
        return carAssistanceService.getCarServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarAssistanceInfoDTO> createCarAssistance(@RequestBody CarAssistanceRequest carAssistance) {
        CarAssistanceInfoDTO createdCarAssistance = carAssistanceService.createCarService(carAssistance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarAssistance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarAssistanceInfoDTO> updateCarAssistance(@PathVariable String id, @RequestBody CarAssistanceRequest carAssistanceRequest) {
        return carAssistanceService.getCarServiceById(id)
                .map(existingCarAssistance -> {
                    CarAssistanceInfoDTO updatedCarAssistance = carAssistanceService.updateCarService(id, carAssistanceRequest);
                    return ResponseEntity.ok(updatedCarAssistance);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarAssistanceInfoDTO> deleteCarAssistance(@PathVariable String id) {
        carAssistanceService.deleteCarService(id);
        return ResponseEntity.noContent().build();
    }
}