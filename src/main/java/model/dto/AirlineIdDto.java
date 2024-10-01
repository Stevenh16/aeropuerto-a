package model.dto;

import java.util.List;

public record AirlineIdDto(int id, String name, String airlineCode, String countryOfOrigin, List<FlightIdDto> flights) {
}
