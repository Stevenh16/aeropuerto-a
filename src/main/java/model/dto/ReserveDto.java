package model.dto;

import java.time.LocalDate;
import java.util.List;

public record ReserveDto(int id, LocalDate date, int numbersOfPassengers, Long client, List<PassengerDto> passengers, List<FlightDto> flights) {
}
