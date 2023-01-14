package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Employee;

import java.util.ArrayList;
import java.util.List;

public record EmployeeInfoDto(
        String id,
        String name,
        String secondName,
        String email,
        double salary,
        String role,
        List<String> listOfCompanies

) {
    public static EmployeeInfoDto of(Employee employee) {
        return new EmployeeInfoDto(
                employee.getId(),
                employee.getName(),
                employee.getSecondName(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getRole().getName(),
                fillListOfCompanies(employee)
        );
    }

    public static List<String> fillListOfCompanies(Employee employee) {
        List<String> listOfCompanies = new ArrayList<>();
        if (employee.getCompanies() != null) {
            employee.getCompanies()
                    .forEach(company -> {
                        listOfCompanies.add(company.getName());
                    });
        }
        return listOfCompanies;
    }
}
