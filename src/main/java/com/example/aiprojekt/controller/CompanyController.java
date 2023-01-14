package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.CompanyInfoDto;
import com.example.aiprojekt.dto.CompanyRequest;
import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<CompanyInfoDto>> getCompanies() {
        List<CompanyInfoDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyInfoDto> getCompanyById(
            @PathVariable String id
    ) {
        CompanyInfoDto companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Company createCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
       companyService.deleteCompanyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id, @RequestBody @Valid CompanyRequest companyRequest ) {
        Company company = companyService.updateComapny(id, companyRequest);
        return ResponseEntity.ok(company);
    }
    @PutMapping("/{companyId}/employees/{emailEmployee}")
    void assignEmployeeToCompany(
            @PathVariable String companyId,
            @PathVariable String emailEmployee
    ) {
        companyService.assignEmployeeToCompany(companyId, emailEmployee);
    }

    @DeleteMapping("/{companyId}/employees/{emailEmployee}")
    void deleteEmployeeFromCompany(
            @PathVariable String companyId,
            @PathVariable String emailEmployee
    ) {
        companyService.deleteEmployeeFromCompany(companyId, emailEmployee);
    }
}
