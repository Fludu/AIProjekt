package com.example.aiprojekt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    private double price;
    @ManyToMany(mappedBy = "carAssistances")
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    public CarAssistance() {

    }

    public CarAssistance(String name, double price) {
        this.name = name;
        this.price = price;

    }


}
