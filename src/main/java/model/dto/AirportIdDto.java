package model.dto;

import java.util.List;

public record AirportIdDto(int id, String name, String city, String country, List<FlightIdDto> flightsOrigins, List<FlightIdDto> flightsDestinations) {
}
