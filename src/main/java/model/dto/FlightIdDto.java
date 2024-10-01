package model.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record FlightIdDto(AirlineIdDto airline, AirportIdDto airportOrigin, AirportIdDto airportDestination, LocalDate exitDate, Time exitTime, Time duration, int capacity, List<ReserveIdDto> reserves) {
}
