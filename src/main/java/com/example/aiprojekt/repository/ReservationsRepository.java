package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, String> {
    boolean existsByDateAndClient(Date date, Client client);

    Reservation findByDateAndClient(Date date, Client client);
}