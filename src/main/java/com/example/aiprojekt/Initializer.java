package com.example.aiprojekt;

import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.Position;
import com.example.aiprojekt.repository.CompanyRepository;
import com.example.aiprojekt.repository.EmployeeRepository;
import com.example.aiprojekt.repository.PositionRepository;
import com.example.aiprojekt.service.CompanyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;
    private final CompanyService companyService;

    @PostConstruct
    public void postConstruct() {
        createPositions();
        createCompanies();
        createEmployees();

    }

    public void createPositions() {
        positionRepository.save(new Position("Kierownik"));
        positionRepository.save(new Position("Programista"));
        positionRepository.save(new Position("Księgowy"));
        positionRepository.save(new Position("Sprzątacz"));
    }

    private void createEmployees() {
        List<Position> allPositions = positionRepository.findAll();
        List<Company> allCompanies = companyRepository.findAll();
        Employee employee = new Employee("Arek", "Fluda", "arek@wp.pl", 10000, allPositions.get(0));
        employeeRepository.save(employee);
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), employee.getEmail());



        employeeRepository.save(new Employee("Kacper", "Roda", "kacper@wp.pl", 10000, allPositions.get(1)));
        employeeRepository.save(new Employee("Kamil", "Zyla", "kamil@wp.pl", 10000, allPositions.get(2)));
        employeeRepository.save(new Employee("Armin", "Bolen", "armin@wp.pl", 10000, allPositions.get(3)));
    }

    private void createCompanies() {
        companyRepository.save(new Company("Asseco", "Rzeszów", "IT"));
        companyRepository.save(new Company("Comarch.SA", "Krakow", "IT"));
        companyRepository.save(new Company("Lufthansa", "Gdansk", "Transport"));
        companyRepository.save(new Company("PKO BP", "Warszaw", "Finance"));
    }
}
