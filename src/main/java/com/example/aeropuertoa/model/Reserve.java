package com.example.aeropuertoa.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reserves")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="date")
    private LocalDate date;
    @Column(name="numbers_of_passengers")
    private int numbersOfPassengers;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy = "reserve", fetch = FetchType.LAZY)
    private List<Passenger> passengers;
    @ManyToMany
    @JoinTable(
            name = "flights_reserves",
            joinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id")
    )
    private List<Flight> flights;
}
