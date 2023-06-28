package com.example.aiprojekt.controller;

import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    private final ReservationsService reservationsService;

    @Autowired
    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationsService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationsById(@PathVariable String id) {
        return reservationsService.getReservationsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservations(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationsService.saveReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservations(@PathVariable String id, @RequestBody Reservation reservation) {
        return reservationsService.getReservationsById(id)
                .map(existingReservations -> {
                    Reservation updatedReservation = reservationsService.updateReservations(id, reservation);
                    return ResponseEntity.ok(updatedReservation);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservations(@PathVariable String id) {
        return ResponseEntity.of(reservationsService.getReservationsById(id));
    }
}
