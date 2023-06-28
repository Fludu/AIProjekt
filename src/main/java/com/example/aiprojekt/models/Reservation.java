package com.example.aiprojekt.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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


    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "reservation_car_assistance",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "car_assistance_id"))
    private List<CarAssistance> carAssistances;


    public Reservation(Date date, List<CarAssistance> carAssistances) {
        this.date = date;
        this.carAssistances = carAssistances;
    }

    public Reservation(Date date, Client client) {
        this.date = date;
        this.client = client;
        this.carAssistances = new ArrayList<>();
    }

    public Reservation(Date date, Client client, List<CarAssistance> carAssistances) {
        this.date = date;
        this.client = client;
        this.carAssistances = carAssistances;
    }
}
