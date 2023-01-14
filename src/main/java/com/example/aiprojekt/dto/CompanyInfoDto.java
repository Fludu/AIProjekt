package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Employee;

import java.util.ArrayList;
import java.util.List;

public record CompanyInfoDto (
        String id,
        String name,
        String city,
        String industryType,
        List<Employee> listOfEmployees
) {
    public static CompanyInfoDto of(Company company) {
        return new CompanyInfoDto(
                company.getId(),
                company.getName(),
                company.getCity(),
                company.getIndustryType(),
                fillListOfEmployees(company)
        );
    }

    public static List<Employee> fillListOfEmployees(Company company) {
        List<Employee> listOfEmployees = new ArrayList<>();
        if (company.getEmployees() != null) {
            listOfEmployees.addAll(company.getEmployees());
        }
        return listOfEmployees;
    }
}
