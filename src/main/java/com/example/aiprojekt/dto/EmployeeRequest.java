package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.Position;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmployeeRequest {

    @NotNull
    String name;
    @NotNull
    String secondName;
    @NotNull
    String email;
    @NotNull
    String position;
    @NotNull
    double salary;


    public static Employee of(EmployeeRequest employeeRequest, Position position) {
        return new Employee(
                employeeRequest.getName(),
                employeeRequest.getSecondName(),
                employeeRequest.getEmail(),
                employeeRequest.getSalary(),
                position
        );
    }
}
