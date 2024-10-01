package model.dto;

import java.util.List;

public record AirlineDto(String name, String airlineCode, String countryOfOrigin, List<FlightDto> flights) {
}
