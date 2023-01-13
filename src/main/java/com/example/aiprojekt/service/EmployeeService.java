package com.example.aiprojekt.service;

import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
