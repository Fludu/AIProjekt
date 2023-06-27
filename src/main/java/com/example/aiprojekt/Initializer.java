package com.example.aiprojekt;

import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservations;
import com.example.aiprojekt.models.CarSAssistance;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.models.User;
import com.example.aiprojekt.repository.RoleRepository;
import com.example.aiprojekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer {
   
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        createRole();
        createUser();
        createCompanies();
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

    public void createJobPosition() {
        jobPositionRepository.save(new CarSAssistance("Kierownik"));
        jobPositionRepository.save(new CarSAssistance("Programista"));
        jobPositionRepository.save(new CarSAssistance("Księgowy"));
        jobPositionRepository.save(new CarSAssistance("Sprzątacz"));
    }

    private void createEmployees() {
        List<CarSAssistance> allCarSAssistances = jobPositionRepository.findAll();
        List<Client> allCompanies = companyRepository.findAll();
        Reservations reservations1 = new Reservations("Arek", "Fluda", "arek@wp.pl", 10000, allCarSAssistances.get(0));
        employeeRepository.save(reservations1);
        Reservations save = employeeRepository.save(new Reservations("Kacper", "Roda", "kacper@wp.pl", 10000, allCarSAssistances.get(1)));
        Reservations save1 = employeeRepository.save(new Reservations("Kamil", "Zyla", "kamil@wp.pl", 10000, allCarSAssistances.get(2)));
        Reservations save2 = employeeRepository.save(new Reservations("Armin", "Bolen", "armin@wp.pl", 10000, allCarSAssistances.get(3)));
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), reservations1.getEmail());
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), save.getEmail());
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), save1.getEmail());
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), save2.getEmail());

    }

    private void createCompanies() {
        companyRepository.save(new Client("Arkadiusz", "Fluda", "arekflu@gmail.com","Rzeszów"));
        companyRepository.save(new Client("Kamil", "Zyla", "kamil.zyla@gmail.com","Warszawa"));
        companyRepository.save(new Client("Kacper", "Roda", "kacper.roda@gmail.com","Krakow"));
    }
}
