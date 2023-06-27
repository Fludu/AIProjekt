package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.ClientNotFoundException;
import com.example.aiprojekt.Exception.NameIsBussyException;
import com.example.aiprojekt.Exception.ReservationNotFound;
import com.example.aiprojekt.dto.ClientDTO;
import com.example.aiprojekt.dto.ClientRequest;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.repository.ClientRepository;
import com.example.aiprojekt.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ReservationsRepository reservationsRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ReservationsRepository reservationsRepository) {
        this.clientRepository = clientRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(ClientDTO::of).collect(Collectors.toList());
    }

    public Optional<Client> getClientById(String id) {
        return clientRepository.findById(id);
    }

    public ClientDTO createClient(ClientRequest client) throws NameIsBussyException {
        if (clientRepository.existsByName(client.getName())) {
            throw new NameIsBussyException(client.getName());
        }
        Client newClient = new Client(client.getName(), client.getLastName(), client.getEmail(), client.getCity());


        return ClientDTO.of(clientRepository.save(newClient));
    }

    public ClientDTO assignReservationToClient(String reservationId, String clientEmail) throws NameIsBussyException {
        Client client = clientRepository.findByEmail(clientEmail).orElseThrow(() -> new ClientNotFoundException(clientEmail));
        Reservation reservation = reservationsRepository.findById(reservationId).orElseThrow(() -> new ReservationNotFound(reservationId));
        if (clientRepository.existsByName(client.getName())) {
            throw new NameIsBussyException(client.getName());
        }
        client.getReservations().add(reservation);


        return ClientDTO.of(clientRepository.save(client));
    }

    public Client updateClient(String id, Client client) {
        client.setClient_id(id);
        return clientRepository.save(client);
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}