package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, String> {
}