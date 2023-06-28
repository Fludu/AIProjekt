package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.ClientByIdNotFoundException;
import com.example.aiprojekt.Exception.NameIsBussyException;
import com.example.aiprojekt.dto.ClientDTO;
import com.example.aiprojekt.dto.ClientRequest;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.repository.ClientRepository;
import com.example.aiprojekt.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOs = new ArrayList<>();

        for (Client client : clients) {
            ClientDTO clientDTO = ClientDTO.of(client);
            clientDTOs.add(clientDTO);
        }

        return clientDTOs;
    }

    public Optional<ClientDTO> getClientById(String id) {
        return clientRepository.findById(id)
                .map(ClientDTO::of);
    }

    public ClientDTO createClient(ClientRequest client) throws NameIsBussyException {
        if (clientRepository.existsByName(client.getName())) {
            throw new NameIsBussyException(client.getName());
        }
        Client newClient = new Client(client.getName(), client.getLastName(), client.getEmail(), client.getCity());


        return ClientDTO.of(clientRepository.save(newClient));
    }

    public Client updateClient(String id, ClientRequest clientRequest) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientByIdNotFoundException(id));
        client.setName(clientRequest.getName());
        client.setCity(clientRequest.getCity());
        client.setLastName(clientRequest.getLastName());
        return clientRepository.save(client);
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}