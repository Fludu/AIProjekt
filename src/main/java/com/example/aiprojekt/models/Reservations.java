package com.example.aiprojekt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Builder
public class Reservations {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    public Reservations() {

    }

    @NotNull
    private String name;
    @NotNull
    private String secondName;
    @Email
    private String email;
    @NotNull
    private double salary;
    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "job_position_id")
    private List<CarAssistance> carAssistances;


    public Reservations(String name, String secondName, String email, double salary, List<CarAssistance> carAssistances) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.salary = salary;
        this.carAssistances = carAssistances;
    }


}
