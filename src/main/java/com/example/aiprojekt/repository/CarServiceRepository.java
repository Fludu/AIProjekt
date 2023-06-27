package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.CarAssistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServiceRepository extends JpaRepository<CarAssistance, String> {
}