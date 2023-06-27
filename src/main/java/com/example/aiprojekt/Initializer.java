package com.example.aiprojekt;

import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.models.User;
import com.example.aiprojekt.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Initializer {
    private final ClientRepository clientRepository;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        createRole();
        createUser();
        createEmployees();

    }

    public void createUser() {
        Role role = roleRepository.findByName("ADMIN").orElseThrow();
        char[] encodedPassword = passwordEncoder.encode("admin").toCharArray();
        userRepository.save(new User("admin@admin", "admin", encodedPassword, role));
    }

    public void createRole() {
        roleRepository.save(new Role("ADMIN"));
    }


    private void createEmployees() {
        Client reservations1 = new Client("Arek", "Fluda", "fluda@wp.pl", "Rzeszow");
        clientRepository.save(reservations1);
        Client reservations2 = new Client("Kacper", "Roda", "roda@wp.pl", "Krk");
        Client reservations3 = new Client("Kamil", "Zyla", "zyla@wp.pl", "Wwa");
        clientRepository.save(reservations2);
        clientRepository.save(reservations3);

    }


}
