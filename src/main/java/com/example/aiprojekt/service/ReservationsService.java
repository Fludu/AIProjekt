package com.example.aiprojekt.service;

import com.example.aiprojekt.models.Reservation;
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

    public List<Reservation> getAllReservations() {
        return reservationsRepository.findAll();
    }

    public Optional<Reservation> getReservationsById(String id) {
        return reservationsRepository.findById(id);
    }

    public Reservation createReservations(Reservation reservation) {
        return reservationsRepository.save(reservation);
    }

    public Reservation updateReservations(String id, Reservation reservation) {
        reservation.setId(id);
        return reservationsRepository.save(reservation);
    }

    public void deleteReservations(String id) {
        reservationsRepository.deleteById(id);
    }
}