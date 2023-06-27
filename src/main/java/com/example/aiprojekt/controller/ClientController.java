package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.ClientDTO;
import com.example.aiprojekt.dto.ClientRequest;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.service.ClientService;
import com.example.aiprojekt.Exception.NameIsBussyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> assignResevationToClient(@RequestBody ClientRequest client) throws NameIsBussyException {
        ClientDTO createdClient = clientService.createClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @PostMapping("/{email}/reservation/{id}")
    public ResponseEntity<ClientDTO> assignResevationToClient(@PathVariable String email, @PathVariable  String reservationId) throws NameIsBussyException {
        ClientDTO createdClient = clientService.assignReservationToClient(reservationId, email);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client client) {
        return clientService.getClientById(id)
                .map(existingClient -> {
                    Client updatedClient = clientService.updateClient(id, client);
                    return ResponseEntity.ok(updatedClient);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable String id) {
        return ResponseEntity.of(clientService.getClientById(id));

    }
}