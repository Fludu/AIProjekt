package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.CompanyInfoDto;
import com.example.aiprojekt.dto.CompanyRequest;
import com.example.aiprojekt.dto.EmployeeInfoDtoWithoutCompany;
import com.example.aiprojekt.models.Company;
import com.example.aiprojekt.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public Company createCompany(
            @Valid @RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id, @RequestBody @Valid CompanyRequest companyRequest) {
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

    @GetMapping("/{companyId}/employees")
    public List<EmployeeInfoDtoWithoutCompany> getEmployeesAssignedToCompany(
            @PathVariable String companyId
    ) {
        return companyService.getEmployeesNotAssignedToCompany(companyId);
    }
}
