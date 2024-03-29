package com.example.aiprojekt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@Builder
public class Company {
    public Company() {
    }

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

    public Company(String name, String city, String industryType) {
        this.name = name;
        this.city = city;
        this.industryType = industryType;

    }

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "Company_Employee",
            joinColumns = {@JoinColumn(name = "company_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @JsonIgnore
    private List<Employee> employees;

    public void assignEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", industryType='" + industryType + '\'' +
                '}';
    }
}
