package com.example.aiprojekt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    private String position;
    @NotNull
    private double salary;
    @ManyToOne
    private Position positions;
    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Company> companies;


}
