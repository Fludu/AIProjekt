package com.example.aiprojekt.controller;

import com.example.aiprojekt.models.Reservations;
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
    public ResponseEntity<List<Reservations>> getAllReservations() {
        List<Reservations> reservations = reservationsService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservations> getReservationsById(@PathVariable String id) {
        return reservationsService.getReservationsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservations> createReservations(@RequestBody Reservations reservations) {
        Reservations createdReservations = reservationsService.createReservations(reservations);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservations> updateReservations(@PathVariable String id, @RequestBody Reservations reservations) {
        return reservationsService.getReservationsById(id)
                .map(existingReservations -> {
                    Reservations updatedReservations = reservationsService.updateReservations(id, reservations);
                    return ResponseEntity.ok(updatedReservations);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservations> deleteReservations(@PathVariable String id) {
        return ResponseEntity.of(reservationsService.getReservationsById(id));
    }
}
