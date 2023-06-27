package com.example.aiprojekt.service;

import com.example.aiprojekt.models.CarService;
import com.example.aiprojekt.repository.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceService {
    private final CarServiceRepository carServiceRepository;

    @Autowired
    public CarServiceService(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    public List<CarService> getAllCarServices() {
        return carServiceRepository.findAll();
    }

    public Optional<CarService> getCarServiceById(String id) {
        return carServiceRepository.findById(id);
    }

    public CarService createCarService(CarService carService) {
        return carServiceRepository.save(carService);
    }

    public CarService updateCarService(String id, CarService carService) {
        carService.setId(id);
        return carServiceRepository.save(carService);
    }

    public void deleteCarService(String id) {
        carServiceRepository.deleteById(id);
    }
}