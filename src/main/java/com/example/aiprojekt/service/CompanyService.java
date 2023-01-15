package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.CompanyNotFoundException;
import com.example.aiprojekt.Exception.EmailBusyException;
import com.example.aiprojekt.Exception.NameBusyException;
import com.example.aiprojekt.dto.CompanyInfoDto;
import com.example.aiprojekt.dto.CompanyRequest;
import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.repository.CompanyRepository;
import com.example.aiprojekt.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;


    public List<CompanyInfoDto> getAllCompanies() {
        return companyRepository.findAll().stream().map(CompanyInfoDto::of).collect(Collectors.toList());
    }

    public CompanyInfoDto getCompanyById(String companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(companyId));
        return CompanyInfoDto.of(company);
    }

    public void deleteCompanyById(String id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        if (company.getEmployees() != null) {
            company.getEmployees()
                    .forEach(employee -> {
                        employee.setCompanies(new ArrayList<>());
                        company.setEmployees(new ArrayList<>());
                    });
        }
        companyRepository.delete(company);
    }

    public Company saveCompany(CompanyRequest companyRequest) {
        if (companyRepository.existsByName(companyRequest.getName())) {
            throw new NameBusyException(companyRequest.getName());
        }
        Company company = Company.builder()
                .name(companyRequest.getName())
                .city(companyRequest.getCity())
                .industryType(companyRequest.getIndustryType()).build();
        return companyRepository.save(company);
    }

    public Company updateComapny(String id, CompanyRequest companyRequest) {
        if (companyRepository.existsByName(companyRequest.getName())) {
            throw new NameBusyException(companyRequest.getName());
        }
        Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        company.setName(companyRequest.getName());
        company.setCity(companyRequest.getCity());
        company.setIndustryType(companyRequest.getIndustryType());
        return companyRepository.save(company);
    }

    public void assignEmployeeToCompany(String companyId, String emailEmployee) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(companyId));
        Employee employee = employeeRepository.findEmployeeByEmail(emailEmployee);

        company.assignEmployee(employee);
        employee.addCompany(company);

        employeeRepository.save(employee);

    }

    @Transactional
    public void deleteEmployeeFromCompany(String companyId, String emailEmployee) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(companyId));
        Employee employee = employeeRepository.findEmployeeByEmail(emailEmployee);

        company.getEmployees().remove(employee);
        employee.getCompanies().remove(company);

    }
}
