package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.*;
import com.example.aiprojekt.dto.ReservationInfoDTO;
import com.example.aiprojekt.dto.ReservationRequest;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.repository.CarAssistanceRepository;
import com.example.aiprojekt.repository.ClientRepository;
import com.example.aiprojekt.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;
    private final ClientRepository clientRepository;
    private final CarAssistanceRepository carAssistanceRepository;
    private final EmailAddAppointmentToUserService emailAddAppointmentToUserService;
    private final EmailCancelAppointmentService emailCancelAppointmentService;

    @Autowired
    public ReservationsService(ReservationsRepository reservationsRepository, ClientRepository clientRepository, EmailAddAppointmentToUserService emailAddAppointmentToUserService, EmailCancelAppointmentService emailCancelAppointmentService,
                               CarAssistanceRepository carAssistanceRepository) {
        this.reservationsRepository = reservationsRepository;
        this.clientRepository = clientRepository;
        this.emailAddAppointmentToUserService = emailAddAppointmentToUserService;
        this.emailCancelAppointmentService = emailCancelAppointmentService;
        this.carAssistanceRepository = carAssistanceRepository;
    }

    public List<ReservationInfoDTO> getAllReservations() {
        return reservationsRepository.findAll().stream().map(ReservationInfoDTO::of).collect(Collectors.toList());

    }

    public ReservationInfoDTO getReservationsById(String id) {
        Reservation reservation = reservationsRepository.findById(id).orElseThrow(() -> new ClientByIdNotFoundException(id));
        return ReservationInfoDTO.of(reservation);
    }

    @Transactional
    public ReservationInfoDTO saveReservation(ReservationRequest reservationRequest) {
        Client client = clientRepository.findById(reservationRequest.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(reservationRequest.getClientId()));

        if (reservationRequest.getDate().before(new Date(System.currentTimeMillis()))) {
            throw new DateFromPastException(reservationRequest.getDate());
        }

        List<CarAssistance> carAssistances = new ArrayList<>();
        if (reservationRequest.getCarAssistanceId() != null) {
            for (String carAssistanceId : reservationRequest.getCarAssistanceId()) {
                CarAssistance carAssistance = carAssistanceRepository.findById(carAssistanceId)
                        .orElseThrow(() -> new CarAssistanceNotFoundException(carAssistanceId));
                carAssistances.add(carAssistance);
            }
        }

        Reservation reservation = ReservationRequest.of(reservationRequest, client, carAssistances);
        reservation.setClient(client);

        Reservation savedReservation = reservationsRepository.save(reservation);

        for (CarAssistance carAssistance : carAssistances) {
            carAssistance.getReservations().add(savedReservation);
        }

        client.getReservations().add(savedReservation);

        EmailAddAppointmentToUserService.EmailAddAppointmentRequest emailAddAppointmentRequest = EmailAddAppointmentToUserService.EmailAddAppointmentRequest.of(savedReservation, client, null); //TODO: ADD CAR ASSISTANCE AFTER END FUNCIONALITY
        emailAddAppointmentToUserService.sendConfirmationEmail(emailAddAppointmentRequest);
        return ReservationInfoDTO.of(savedReservation);
    }

    public ReservationInfoDTO updateReservations(String id, ReservationRequest reservationRequest) {
        Client clientById = clientRepository.findById(id).orElse(null);

        if (reservationsRepository.existsByDateAndClient(reservationRequest.getDate(), clientById)) {
            throw new ReservationExsitsException(reservationRequest.getDate(), (clientById.getClient_id()));
        }
        Reservation reservation = reservationsRepository.findByDateAndClient(reservationRequest.getDate(), clientById);
        if (reservation != null) {
            reservation.setDate(reservationRequest.getDate());
            reservation.setClient(clientById);
            reservationsRepository.save(reservation);
        }
        return ReservationInfoDTO.of(reservation);
    }

    public void deleteReservations(String id) {
        Reservation reservationByEmail = reservationsRepository.findById(id).orElseThrow(() -> new ReservationNotFound(id));
        if (reservationByEmail != null) {
            if (reservationByEmail.getCarAssistances() != null) {
                reservationByEmail.getCarAssistances().forEach(carAssistance -> {
                    reservationByEmail.setCarAssistances(new ArrayList<>());
                    carAssistance.setReservations(new ArrayList<>());
                    reservationByEmail.setCarAssistances(new ArrayList<>());
                });
            }
        }
        EmailCancelAppointmentService.EmailCancelAppointmentRequest emailCancelAppointmentRequest = new EmailCancelAppointmentService.EmailCancelAppointmentRequest(Reservation.builder().build(), Client.builder().build(), CarAssistance.builder().build());
        emailCancelAppointmentService.sendConfirmationEmail(emailCancelAppointmentRequest);

        reservationsRepository.deleteById(reservationByEmail.getId());
    }
}