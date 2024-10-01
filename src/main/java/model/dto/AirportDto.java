package model.dto;

import java.util.List;

public record AirportDto(String name, String city, String country, List<FlightDto> flightsOrigins, List<FlightDto> flightsDestinations) {
}
