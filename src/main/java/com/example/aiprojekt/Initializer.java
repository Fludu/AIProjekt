package com.example.aiprojekt;

import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.JobPosition;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.models.User;
import com.example.aiprojekt.repository.CompanyRepository;
import com.example.aiprojekt.repository.EmployeeRepository;
import com.example.aiprojekt.repository.JobPositionRepository;
import com.example.aiprojekt.repository.RoleRepository;
import com.example.aiprojekt.repository.UserRepository;
import com.example.aiprojekt.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final JobPositionRepository jobPositionRepository;
    private final CompanyService companyService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        createRole();
        createUser();
        createJobPosition();
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
        jobPositionRepository.save(new JobPosition("Kierownik"));
        jobPositionRepository.save(new JobPosition("Programista"));
        jobPositionRepository.save(new JobPosition("Księgowy"));
        jobPositionRepository.save(new JobPosition("Sprzątacz"));
    }

    private void createEmployees() {
        List<JobPosition> allJobPositions = jobPositionRepository.findAll();
        List<Company> allCompanies = companyRepository.findAll();
        Employee employee = new Employee("Arek", "Fluda", "arek@wp.pl", 10000, allJobPositions.get(0));
        employeeRepository.save(employee);
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), employee.getEmail());


        employeeRepository.save(new Employee("Kacper", "Roda", "kacper@wp.pl", 10000, allJobPositions.get(1)));
        employeeRepository.save(new Employee("Kamil", "Zyla", "kamil@wp.pl", 10000, allJobPositions.get(2)));
        employeeRepository.save(new Employee("Armin", "Bolen", "armin@wp.pl", 10000, allJobPositions.get(3)));
    }

    private void createCompanies() {
        companyRepository.save(new Company("Asseco", "Rzeszów", "IT"));
        companyRepository.save(new Company("Comarch.SA", "Krakow", "IT"));
        companyRepository.save(new Company("Lufthansa", "Gdansk", "Transport"));
        companyRepository.save(new Company("PKO BP", "Warszaw", "Finance"));
    }
}
