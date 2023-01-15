package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Employee;

public record EmployeeInfoDtoWithoutCompany(
        String id,
        String name,
        String secondName,
        String email,
        double salary,
        String jobPosition
) {
    public static EmployeeInfoDtoWithoutCompany of(Employee employee) {
        return new EmployeeInfoDtoWithoutCompany(
                employee.getId(),
                employee.getName(),
                employee.getSecondName(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getJobPosition().getName()
        );
    }
}
