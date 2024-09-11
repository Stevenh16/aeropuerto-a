package com.example.aeropuertoa.model;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="airline_id")
    private Airline airline;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_origin_id")
    private Airport airport_origin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_destination_id")
    private Airport airport_destination;
    @Column(name="exit_date")
    private LocalDateTime exitDate;
    @Column(name="exit_time")
    private LocalDateTime exitTime;
    @Column(name="duration")
    private LocalDateTime duration;
    @Column(name="capacity")
    private int capacity;
    @ManyToMany(mappedBy = "flights")
    private List<Reserve> reserves;
}
