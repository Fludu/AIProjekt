package com.example.aiprojekt.models;

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
    private List<Employee> employees;

    public Position() {

    }
}
