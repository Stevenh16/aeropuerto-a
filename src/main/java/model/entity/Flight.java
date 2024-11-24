package model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
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
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="airline_id")
    private Airline airline;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_origin_id")
    private Airport airportOrigin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_destination_id")
    private Airport airportDestination;
    @Column(name="exit_date")
    private LocalDate exitDate;
    @Column(name="exit_time")
    private Time exitTime;
    @Column(name="duration")
    private Time duration;
    @Column(name="capacity")
    private int capacity;
    @ManyToMany(mappedBy = "flights")
    private List<Reserve> reserves;
}
