package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.CarAssistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarAssistanceRepository extends JpaRepository<CarAssistance, String> {
    boolean existsByName(String name);
    CarAssistance findByName(String name);
}