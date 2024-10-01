package model.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record FlightDto(AirlineDto airline, AirportDto airportOrigin, AirportDto airportDestination, LocalDate exitDate, Time exitTime, Time duration, int capacity, List<ReserveDto> reserves) {
}
