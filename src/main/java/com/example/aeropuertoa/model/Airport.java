package com.example.aeropuertoa.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @OneToMany(mappedBy = "airport_origin", fetch = FetchType.LAZY)
    private List<Flight> flightsOrigins;
    @OneToMany(mappedBy = "airport_destination", fetch = FetchType.LAZY)
    private List<Flight> flightsDestinations;
}
