package com.example.aiprojekt;

import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.models.User;
import com.example.aiprojekt.repository.CarAssistanceRepository;
import com.example.aiprojekt.repository.ClientRepository;
import com.example.aiprojekt.repository.RoleRepository;
import com.example.aiprojekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class Initializer {
    private final ClientRepository clientRepository;
    private final CarAssistanceRepository carAssistanceRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        createRole();
        createUser();
        createCarAssistance();
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
        Client reservations1 = new Client("Arek", "Fluda", "arekflu@gmail.com", "Rzeszow");
        clientRepository.save(reservations1);
        Client reservations2 = new Client("Kacper", "Roda", "kacper.roda37@gmail.com", "Krk");
        Client reservations3 = new Client("Kamil", "Zyla", "kamilzyla11@gmail.com", "Wwa");
        clientRepository.save(reservations2);
        clientRepository.save(reservations3);

    }

    private void createCarAssistance() {
        CarAssistance reservations1 = new CarAssistance("12", "Wymiana opon", 200, new ArrayList<>());
        carAssistanceRepository.save(reservations1);
        CarAssistance reservations2 = new CarAssistance("Naprawa hamulcow", 400);
        CarAssistance reservations3 = new CarAssistance("Naprawa silnika", 800);
        CarAssistance reservations4 = new CarAssistance("Przeglad samochodu", 1200);

        carAssistanceRepository.save(reservations2);
        carAssistanceRepository.save(reservations3);
        carAssistanceRepository.save(reservations4);
    }


}
