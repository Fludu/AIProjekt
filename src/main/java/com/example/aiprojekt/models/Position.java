package com.example.aiprojekt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Position {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    private String name;

    @OneToMany
    @JsonIgnore
    private List<Employee> employees;

    public Position() {

    }

    public Position (String name) {
        this.name = name;
    }

    public void addEmployee (Employee employee) {
        employees.add(employee);
    }
}
