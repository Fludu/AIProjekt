package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.CompanyNotFoundException;
import com.example.aiprojekt.dto.CompanyRequest;
import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompanyById(String id) {
        companyRepository.deleteById(id);
    }

    public Company saveCompany(CompanyRequest companyRequest) {
        Company company = Company.builder()
                .name(companyRequest.getName())
                .city(companyRequest.getCity())
                .industryType(companyRequest.getIndustryType()).build();
        return companyRepository.save(company);
    }

    public Company updateComapny(String id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        company.setName(companyRequest.getName());
        company.setCity(companyRequest.getCity());
        company.setIndustryType(companyRequest.getIndustryType());
        return companyRepository.save(company);
    }

    public Company getCompanyById(String id) {
        return (companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id)));
    }

}
