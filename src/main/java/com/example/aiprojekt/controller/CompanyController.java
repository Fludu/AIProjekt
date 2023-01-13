package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.CompanyRequest;
import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(CompanyRequest companyRequest) {
        Company company = companyService.saveCompany(companyRequest);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable String id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id, @RequestBody CompanyRequest companyRequest ) {
        Company company = companyService.updateComapny(id, companyRequest);
        return ResponseEntity.ok(company);
    }


}
