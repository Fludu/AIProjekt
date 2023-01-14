package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.EmailBusyException;
import com.example.aiprojekt.Exception.PositionNotFoundException;
import com.example.aiprojekt.dto.EmployeeInfoDto;
import com.example.aiprojekt.dto.EmployeeRequest;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.Position;
import com.example.aiprojekt.repository.EmployeeRepository;
import com.example.aiprojekt.repository.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeByEmail(String email) {
        Employee employeeByEmail = employeeRepository.findEmployeeByEmail(email);
        employeeRepository.delete(employeeByEmail);
    }

    @Transactional
    public EmployeeInfoDto saveEmployee(EmployeeRequest employee) {
        Position position = positionRepository.findByName(employee.getPosition());

        if(position == null) {
            throw new PositionNotFoundException(employee.getPosition());
        }
        if(employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmailBusyException(employee.getEmail());
        }
        Employee savedEmployee = employeeRepository.save(EmployeeRequest.of(employee, position));
        position.addEmployee(savedEmployee);

        return EmployeeInfoDto.of(savedEmployee);
    }

    @Transactional
    public Employee updateEmployee(String email, EmployeeRequest employeeRequest) {
        Employee employeeByEmail = employeeRepository.findEmployeeByEmail(email);
        Position position = positionRepository.findByName(employeeRequest.getPosition());

        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new EmailBusyException(employeeRequest.getEmail());
        }

        employeeByEmail.setEmail(employeeRequest.getEmail());
        employeeByEmail.setSalary(employeeRequest.getSalary());
        employeeByEmail.setName(employeeRequest.getName());
        employeeByEmail.setPositions(position);
        employeeByEmail.setSecondName(employeeRequest.getSecondName());

        return employeeByEmail;
    }
}
