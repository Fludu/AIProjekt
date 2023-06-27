package com.example.aiprojekt.service;

import com.example.aiprojekt.models.CarAssistance;
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

    public List<CarAssistance> getAllCarServices() {
        return carServiceRepository.findAll();
    }

    public Optional<CarAssistance> getCarServiceById(String id) {
        return carServiceRepository.findById(id);
    }

    public CarAssistance createCarService(CarAssistance carAssistance) {
        return carServiceRepository.save(carAssistance);
    }

    public CarAssistance updateCarService(String id, CarAssistance carAssistance) {
        carAssistance.setId(id);
        return carServiceRepository.save(carAssistance);
    }

    public void deleteCarService(String id) {
        carServiceRepository.deleteById(id);
    }
}