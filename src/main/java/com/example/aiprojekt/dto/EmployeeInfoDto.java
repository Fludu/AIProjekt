package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Employee;

public record EmployeeInfoDto(
        String id,
        String name,
        String secondName,
        String email,
        double salary,
        String position

) {
    public static EmployeeInfoDto of(Employee employee) {
        return new EmployeeInfoDto(
                employee.getId(),
                employee.getName(),
                employee.getSecondName(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getPositions().getName()
        );
    }
}
