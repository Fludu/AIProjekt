package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, String> {
}