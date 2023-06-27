package com.example.aiprojekt.service;

import com.example.aiprojekt.dto.CarAssistanceDTO;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.repository.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarAssistanceService {
    private final CarServiceRepository carServiceRepository;

    @Autowired
    public CarAssistanceService(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    public List<CarAssistanceDTO> getAllCarServices() {
        return carServiceRepository.findAll().stream().map(CarAssistanceDTO::of).collect(Collectors.toList());
    }

    public Optional<CarAssistanceDTO> getCarServiceById(String id) {
        return carServiceRepository.findById(id).map(CarAssistanceDTO::of);
    }

    public CarAssistanceDTO createCarService(CarAssistance carAssistance) {



        return CarAssistanceDTO.of(carServiceRepository.save(carAssistance));
    }

    public CarAssistance updateCarService(String id, CarAssistance carAssistance) {
        carAssistance.setId(id);
        return carServiceRepository.save(carAssistance);
    }

    public void deleteCarService(String id) {
        carServiceRepository.deleteById(id);
    }
}