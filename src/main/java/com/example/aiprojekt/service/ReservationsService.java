package com.example.aiprojekt.service;

import com.example.aiprojekt.dto.ReservationInfoDTO;
import com.example.aiprojekt.dto.ReservationRequest;
import com.example.aiprojekt.exception.CarAssistanceNotFoundException;
import com.example.aiprojekt.exception.ClientByIdNotFoundException;
import com.example.aiprojekt.exception.ClientNotFoundException;
import com.example.aiprojekt.exception.DateFromPastException;
import com.example.aiprojekt.exception.ReservationNotFound;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.repository.CarAssistanceRepository;
import com.example.aiprojekt.repository.ClientRepository;
import com.example.aiprojekt.repository.ReservationsRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

        if (reservationRequest.getDate().isBefore(LocalDateTime.now())) {
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

        EmailAddAppointmentToUserService.EmailAddAppointmentRequest emailAddAppointmentRequest = EmailAddAppointmentToUserService.EmailAddAppointmentRequest.of(savedReservation, client, getCarAssistanceInfo(carAssistances));
        emailAddAppointmentToUserService.sendConfirmationEmail(emailAddAppointmentRequest);
        return ReservationInfoDTO.of(savedReservation);
    }

    public ReservationInfoDTO updateReservations(String id, ReservationRequest reservationRequest) {
        Reservation reservation = reservationsRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFound(id));

        if (reservationRequest.getDate().isBefore(LocalDateTime.now())) {
            throw new DateFromPastException(reservationRequest.getDate());
        }

        Client client = clientRepository.findById(reservationRequest.getClientId())
                .orElseThrow(() -> new ClientByIdNotFoundException(reservationRequest.getClientId()));

        List<CarAssistance> carAssistances = new ArrayList<>();
        if (reservationRequest.getCarAssistanceId() != null) {
            for (String carAssistanceId : reservationRequest.getCarAssistanceId()) {
                CarAssistance carAssistance = carAssistanceRepository.findById(carAssistanceId)
                        .orElseThrow(() -> new CarAssistanceNotFoundException(carAssistanceId));
                carAssistances.add(carAssistance);
            }
        }

        reservation.setDate(reservationRequest.getDate());
        reservation.setClient(client);
        reservation.setCarAssistances(carAssistances);

        Reservation updatedReservation = reservationsRepository.save(reservation);

        return ReservationInfoDTO.of(updatedReservation);
    }

    //TODO
    public void deleteReservations(String id) {
        Reservation reservationByEmail = reservationsRepository.findById(id).orElseThrow(() -> new ReservationNotFound(id));
        if (reservationByEmail != null) {
            if (reservationByEmail.getCarAssistances() != null) {
                EmailCancelAppointmentService.EmailCancelAppointmentRequest emailCancelAppointmentRequest = new EmailCancelAppointmentService.EmailCancelAppointmentRequest(reservationByEmail, reservationByEmail.getClient(), reservationByEmail.getCarAssistances().stream().map(CarAssistance::getName).toList());
                emailCancelAppointmentService.sendConfirmationEmail(emailCancelAppointmentRequest);
                reservationByEmail.getCarAssistances().forEach(carAssistance -> {
                    reservationByEmail.setCarAssistances(new ArrayList<>());
                    carAssistance.setReservations(new ArrayList<>());
                    reservationByEmail.setCarAssistances(new ArrayList<>());
                });
            }
        }


        reservationsRepository.deleteById(reservationByEmail.getId());
    }

    Pair<List<String>, Double> getCarAssistanceInfo(List<CarAssistance> carAssistances) {
        List<String> names = new ArrayList<>();
        double totalPrice = 0.0;

        for (CarAssistance carAssistance : carAssistances) {
            names.add(carAssistance.getName());
            totalPrice += carAssistance.getPrice();
        }

        return Pair.of(names, totalPrice);
    }
}