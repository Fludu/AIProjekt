package com.example.aiprojekt.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@Builder
public class Client {
    public Client() {
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String client_id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String city;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Client(String name, String lastName, String email, String city) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.reservations = new ArrayList<>();


    }


    public void assignEmployee(Reservation reservation) {
        this.reservations.add(reservation);
    }


}
