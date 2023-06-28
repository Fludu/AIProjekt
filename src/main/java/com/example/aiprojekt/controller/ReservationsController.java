package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.ReservationInfoDTO;
import com.example.aiprojekt.dto.ReservationRequest;
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
    public ResponseEntity<List<ReservationInfoDTO>> getAllReservations() {
        List<ReservationInfoDTO> reservations = reservationsService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationInfoDTO> getReservationsById(@PathVariable String id) {
        ReservationInfoDTO reservationInfoDTO = reservationsService.getReservationsById(id);
        return ResponseEntity.ok(reservationInfoDTO);
    }

    @PostMapping
    public ResponseEntity<ReservationInfoDTO> createReservations(@RequestBody ReservationRequest reservation) {
        ReservationInfoDTO createdReservation = reservationsService.saveReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationInfoDTO> updateReservations(@PathVariable String id, @RequestBody ReservationRequest reservation) {
        ReservationInfoDTO reservationsById = reservationsService.updateReservations(id, reservation);
        return ResponseEntity.ok(reservationsById);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationInfoDTO> deleteReservations(@PathVariable String id) {
        reservationsService.deleteReservations(id);
        return ResponseEntity.noContent().build();
    }
}
