package model.dto;

import java.time.LocalDate;
import java.util.List;

public record ReserveIdDto(int id, LocalDate date, int numbersOfPassengers, ClientIdDto client, List<PassengerIdDto> passengers, List<FlightIdDto> flights) {
}
