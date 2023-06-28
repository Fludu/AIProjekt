package com.example.aiprojekt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Builder
public class Reservation {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "reservationId")
    private String id;

    public Reservation() {

    }


    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "reservation_car_assistance",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "car_assistance_id"))
    private List<CarAssistance> carAssistances;


    public Reservation(LocalDateTime localDateTime, List<CarAssistance> carAssistances) {
        this.localDateTime = localDateTime;
        this.carAssistances = carAssistances;
    }

    public Reservation(LocalDateTime localDateTime, Client client) {
        this.localDateTime = localDateTime;
        this.client = client;
        this.carAssistances = new ArrayList<>();
    }
}
