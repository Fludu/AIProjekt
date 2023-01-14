package com.example.aiprojekt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Builder
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    public Employee() {

    }

    @NotNull
    private String name;
    @NotNull
    private String secondName;
    @Email
    private String email;
    @NotNull
    private double salary;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Company> companies;


    public Employee(String name, String secondName, String email, double salary, Role role) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.salary = salary;
        this.role = role;
        this.companies = new ArrayList<>();
    }

    public void addCompany(Company company) {
        companies.add(company);
    }


}
