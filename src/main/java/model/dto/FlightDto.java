package model.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record FlightDto(int id, Long airline, Long airportOrigin, Long airportDestination, LocalDate exitDate, Time exitTime, Time duration, int capacity, List<Long> reserves) {
}
