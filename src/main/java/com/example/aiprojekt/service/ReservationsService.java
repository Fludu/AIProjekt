package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.ClientNotFoundException;
import com.example.aiprojekt.Exception.ReservationNotFound;
import com.example.aiprojekt.dto.ReservationRequest;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.repository.ClientRepository;
import com.example.aiprojekt.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;
    private final EmailAddAppointmentToUserService emailAddAppointmentToUserService;
    private final EmailCancelAppointmentService emailCancelAppointmentService;
    private final ClientRepository clientRepository;

    @Autowired
    public ReservationsService(ReservationsRepository reservationsRepository, EmailAddAppointmentToUserService emailAddAppointmentToUserService, EmailCancelAppointmentService emailCancelAppointmentService, ClientRepository clientRepository) {
        this.reservationsRepository = reservationsRepository;
        this.emailAddAppointmentToUserService = emailAddAppointmentToUserService;
        this.emailCancelAppointmentService = emailCancelAppointmentService;
        this.clientRepository = clientRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationsRepository.findAll();
    }

    public Optional<Reservation> getReservationsById(String id) {
        return reservationsRepository.findById(id);
    }

    public Reservation saveReservation(ReservationRequest reservation) {
        Client client = clientRepository.findById(reservation.getC().getEmail()).orElseThrow(() -> new ClientNotFoundException(reservation.getClient().getEmail()));
        Reservation reservation1 = reservationsRepository.save(null);
        EmailAddAppointmentToUserService.EmailAddAppointmentRequest emailAddAppointmentRequest = EmailAddAppointmentToUserService.EmailAddAppointmentRequest.of(reservation1, client, null); //TODO: ADD CAR ASSISTANCE AFTER END FUNCIONALITY
        emailAddAppointmentToUserService.sendConfirmationEmail(emailAddAppointmentRequest);
        return reservationsRepository.save(reservation);
    }

    public Reservation updateReservations(String id, Reservation reservation) {
        reservation.setId(id);
        Optional<Reservation> reservationById = reservationsRepository.findById(id);

        return reservationsRepository.save(reservation);
    }

    public void deleteReservations(String id) {
        Reservation reservationByEmail = reservationsRepository.findById(id).orElseThrow(() -> new ReservationNotFound(id));
        if (reservationByEmail != null) {
            if (reservationByEmail.getCarAssistances() != null) {
                reservationByEmail.getCarAssistances().forEach(carAssistance -> {
                    reservationByEmail.setCarAssistances(new ArrayList<>());
                    carAssistance.setReservations(new ArrayList<>());
                });
            }
        }
        reservationsRepository.deleteById(reservationByEmail.getId());
        EmailCancelAppointmentService.EmailCancelAppointmentRequest emailCancelAppointmentRequest = new EmailCancelAppointmentService.EmailCancelAppointmentRequest(Reservation.builder().build(), Client.builder().build(), CarAssistance.builder().build());
        emailCancelAppointmentService.sendConfirmationEmail(emailCancelAppointmentRequest);
    }
}