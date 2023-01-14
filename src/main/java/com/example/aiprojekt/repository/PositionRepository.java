package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
    public Position findByName(String name);
}
