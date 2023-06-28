package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.CarAssistanceExistsException;
import com.example.aiprojekt.Exception.CarAssistanceNotFoundException;
import com.example.aiprojekt.dto.CarAssistanceInfoDTO;
import com.example.aiprojekt.dto.CarAssistanceRequest;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.repository.CarAssistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarAssistanceService {
    private final CarAssistanceRepository carAssistanceRepository;

    @Autowired
    public CarAssistanceService(CarAssistanceRepository carAssistanceRepository) {
        this.carAssistanceRepository = carAssistanceRepository;
    }

    public List<CarAssistanceInfoDTO> getAllCarServices() {
        return carAssistanceRepository.findAll().stream().map(CarAssistanceInfoDTO::of).collect(Collectors.toList());
    }

    public Optional<CarAssistanceInfoDTO> getCarServiceById(String id) {
        return carAssistanceRepository.findById(id).map(CarAssistanceInfoDTO::of);
    }

    public CarAssistanceInfoDTO createCarService(CarAssistanceRequest carAssistanceRequest) {
        if (carAssistanceRepository.existsByName(carAssistanceRequest.getName())) {
            throw new CarAssistanceExistsException(carAssistanceRequest.getName());
        }
        CarAssistance carAssistance = new CarAssistance(carAssistanceRequest.getName(), carAssistanceRequest.getPrice());
        return CarAssistanceInfoDTO.of(carAssistanceRepository.save(carAssistance));
    }

    public CarAssistanceInfoDTO updateCarService(String id, CarAssistanceRequest carAssistanceRequest) {
        CarAssistance carAssistance = carAssistanceRepository.findById(id).orElseThrow(() -> new CarAssistanceNotFoundException(id));
        carAssistance.setName(carAssistanceRequest.getName());
        carAssistance.setPrice(carAssistanceRequest.getPrice());
        return CarAssistanceInfoDTO.of(carAssistanceRepository.save(carAssistance));
    }

    public void deleteCarService(String id) {
        CarAssistance carAssistance = carAssistanceRepository.findById(id).orElseThrow(() -> new CarAssistanceNotFoundException(id));
        carAssistanceRepository.deleteById(carAssistance.getId());
    }
}