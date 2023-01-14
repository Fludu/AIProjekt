package com.example.aiprojekt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Builder
public class Company {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String industryType;

    public Company() {

    }

    @ManyToMany(mappedBy = "companies")
    @JsonIgnore
    private List<Employee> employees;

    public void assignEmployee(Employee employee) {
        employees.add(employee);
    }
}
