package com.example.aiprojekt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;
    @ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Company> companies;


    public Employee(String name, String secondName, String email, double salary, JobPosition jobPosition) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.salary = salary;
        this.jobPosition = jobPosition;
        this.companies = new ArrayList<>();
    }

    public void addCompany(Company company) {
        companies.add(company);
    }


}
