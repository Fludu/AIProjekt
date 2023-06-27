package com.example.aiprojekt.service;

import com.example.aiprojekt.models.Reservations;
import com.example.aiprojekt.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;

    @Autowired
    public ReservationsService(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }

    public Optional<Reservations> getReservationsById(String id) {
        return reservationsRepository.findById(id);
    }

    public Reservations createReservations(Reservations reservations) {
        return reservationsRepository.save(reservations);
    }

    public Reservations updateReservations(String id, Reservations reservations) {
        reservations.setId(id);
        return reservationsRepository.save(reservations);
    }

    public void deleteReservations(String id) {
        reservationsRepository.deleteById(id);
    }
}