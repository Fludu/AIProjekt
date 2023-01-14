package com.example.aiprojekt;

import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.repository.CompanyRepository;
import com.example.aiprojekt.repository.EmployeeRepository;
import com.example.aiprojekt.repository.RoleRepository;
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
    private final RoleRepository roleRepository;
    private final CompanyService companyService;

    @PostConstruct
    public void postConstruct() {
        createRole();
        createCompanies();
        createEmployees();

    }

    public void createRole() {
        roleRepository.save(new Role("Kierownik"));
        roleRepository.save(new Role("Programista"));
        roleRepository.save(new Role("Księgowy"));
        roleRepository.save(new Role("Sprzątacz"));
    }

    private void createEmployees() {
        List<Role> allRoles = roleRepository.findAll();
        List<Company> allCompanies = companyRepository.findAll();
        Employee employee = new Employee("Arek", "Fluda", "arek@wp.pl", 10000, allRoles.get(0));
        employeeRepository.save(employee);
        companyService.assignEmployeeToCompany(allCompanies.get(0).getId(), employee.getEmail());



        employeeRepository.save(new Employee("Kacper", "Roda", "kacper@wp.pl", 10000, allRoles.get(1)));
        employeeRepository.save(new Employee("Kamil", "Zyla", "kamil@wp.pl", 10000, allRoles.get(2)));
        employeeRepository.save(new Employee("Armin", "Bolen", "armin@wp.pl", 10000, allRoles.get(3)));
    }

    private void createCompanies() {
        companyRepository.save(new Company("Asseco", "Rzeszów", "IT"));
        companyRepository.save(new Company("Comarch.SA", "Krakow", "IT"));
        companyRepository.save(new Company("Lufthansa", "Gdansk", "Transport"));
        companyRepository.save(new Company("PKO BP", "Warszaw", "Finance"));
    }
}
