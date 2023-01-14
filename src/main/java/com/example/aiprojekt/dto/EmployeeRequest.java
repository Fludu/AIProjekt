package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmployeeRequest {

    @NotBlank
    String name;
    @NotBlank
    String secondName;
    @Email
    String email;
    @NotBlank
    String role;
    @NotNull
    double salary;


    public static Employee of(EmployeeRequest employeeRequest, Role role) {
        return new Employee(
                employeeRequest.getName(),
                employeeRequest.getSecondName(),
                employeeRequest.getEmail(),
                employeeRequest.getSalary(),
                role
        );
    }
}
