package model.dto;

import java.util.List;

public record AirlineDto(int id,String name, String airlineCode, String countryOfOrigin, List<FlightDto> flights) {
}
