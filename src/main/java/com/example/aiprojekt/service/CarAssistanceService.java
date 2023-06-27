package com.example.aiprojekt.service;

import com.example.aiprojekt.models.CarSAssistance;
import com.example.aiprojekt.repository.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarAssistanceService {
    private final CarServiceRepository carServiceRepository;

    @Autowired
    public CarAssistanceService(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    public List<CarSAssistance> getAllCarServices() {
        return carServiceRepository.findAll();
    }

    public Optional<CarSAssistance> getCarServiceById(String id) {
        return carServiceRepository.findById(id);
    }

    public CarSAssistance createCarService(CarSAssistance carSAssistance) {
        return carServiceRepository.save(carSAssistance);
    }

    public CarSAssistance updateCarService(String id, CarSAssistance carSAssistance) {
        carSAssistance.setId(id);
        return carServiceRepository.save(carSAssistance);
    }

    public void deleteCarService(String id) {
        carServiceRepository.deleteById(id);
    }
}