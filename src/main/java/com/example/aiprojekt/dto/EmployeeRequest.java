package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.models.JobPosition;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EmployeeRequest {

    @NotBlank
    String name;
    @NotBlank
    String secondName;
    @Email
    String email;
    @NotBlank
    String jobPositionId;
    @NotNull
    double salary;


    public static Employee of(EmployeeRequest employeeRequest, JobPosition jobPosition) {
        return new Employee(
                employeeRequest.getName(),
                employeeRequest.getSecondName(),
                employeeRequest.getEmail(),
                employeeRequest.getSalary(),
                jobPosition
        );
    }
}
