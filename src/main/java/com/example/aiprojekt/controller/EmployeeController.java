package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.EmployeeInfoDto;
import com.example.aiprojekt.dto.EmployeeRequest;
import com.example.aiprojekt.models.Employee;
import com.example.aiprojekt.service.EmployeeService;
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
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeInfoDto>> getEmployees() {
        List<EmployeeInfoDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeInfoDto> getEmployeeById(
            @PathVariable String id
    ) {
        EmployeeInfoDto employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeInfoDto createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.saveEmployee(employeeRequest);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployeeByEmail(email);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{email}")
    public ResponseEntity<EmployeeInfoDto> updateEmployee(@PathVariable String email, @RequestBody @Valid EmployeeRequest employeeRequest) {
        EmployeeInfoDto employee = employeeService.updateEmployee(email, employeeRequest);
        return ResponseEntity.ok(employee);
    }
}
