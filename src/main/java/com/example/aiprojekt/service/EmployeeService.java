package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.EmailBusyException;
import com.example.aiprojekt.Exception.EmployeeNotFoundException;
import com.example.aiprojekt.Exception.RoleNotFoundException;
import com.example.aiprojekt.Exception.SalaryMustBePositive;
import com.example.aiprojekt.dto.EmployeeInfoDto;
import com.example.aiprojekt.dto.EmployeeRequest;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.repository.EmployeeRepository;
import com.example.aiprojekt.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;


    public List<EmployeeInfoDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream().map(EmployeeInfoDto::of).collect(Collectors.toList());
    }

    public EmployeeInfoDto getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return EmployeeInfoDto.of(employee);
    }

    public void deleteEmployeeByEmail(String email) {
        Employee employeeByEmail = employeeRepository.findEmployeeByEmail(email);
        if (employeeByEmail != null) {
            if (employeeByEmail.getCompanies() != null) {
                employeeByEmail.getCompanies()
                        .forEach(company -> {
                            company.setEmployees(new ArrayList<>());
                            employeeByEmail.setCompanies(new ArrayList<>());
                        });
            }
            employeeRepository.delete(employeeByEmail);
        }
    }

    @Transactional
    public EmployeeInfoDto saveEmployee(EmployeeRequest employee) {
        Role role = roleRepository.findByName(employee.getRole());

        if (role == null) {
            throw new RoleNotFoundException(employee.getRole());
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmailBusyException(employee.getEmail());
        }

        if (employee.getSalary() <= 0) {
            throw new SalaryMustBePositive();
        }

        Employee savedEmployee = employeeRepository.save(EmployeeRequest.of(employee, role));
        role.addEmployee(savedEmployee);

        return EmployeeInfoDto.of(savedEmployee);
    }

    @Transactional
    public Employee updateEmployee(String email, EmployeeRequest employeeRequest) {

        if (employeeRepository.existsByEmail(employeeRequest.getEmail()) && !employeeRequest.getEmail().equals(email)) {
            throw new EmailBusyException(employeeRequest.getEmail());
        }

        Employee employeeByEmail = employeeRepository.findEmployeeByEmail(email);
        Role role = roleRepository.findByName(employeeRequest.getRole());

        if (employeeByEmail != null) {
            employeeByEmail.setEmail(employeeRequest.getEmail());
            if (employeeRequest.getSalary() <= 0) {
                throw new SalaryMustBePositive();
            }
            employeeByEmail.setSalary(employeeRequest.getSalary());
            employeeByEmail.setName(employeeRequest.getName());
            employeeByEmail.setRole(role);
            employeeByEmail.setSecondName(employeeRequest.getSecondName());
        }
        return employeeByEmail;
    }
}
