package com.example.aiprojekt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Builder
public class CarAssistance {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @OneToMany
    @JsonIgnore
    private List<Reservations> reservations;

    public CarAssistance() {

    }

    public CarAssistance(String name) {
        this.name = name;
        this.reservations = new ArrayList<>();
    }

    public void addEmployee(Reservations reservations) {
        this.reservations.add(reservations);
    }
}
