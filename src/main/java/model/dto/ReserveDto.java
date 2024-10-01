package model.dto;

import java.time.LocalDate;
import java.util.List;

public record ReserveDto(LocalDate date, int numbersOfPassengers, ClientDto client, List<PassengerDto> passengers, List<FlightDto> flights) {
}
