package model.mapper;

import lombok.AllArgsConstructor;
import model.dto.FlightDto;
import model.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class FlightMapper {
    private AirlineMapper airlineMapper;
    private AirportMapper airportMapper;
    private ReserveMapper reserveMapper;
    public Flight toEntity(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setReserves(reserveMapper.toEntities(flightDto.reserves()));
        flight.setAirline(airlineMapper.toEntity(flightDto.airline()));
        flight.setCapacity(flightDto.capacity());
        flight.setAirport_origin(airportMapper.toEntity(flightDto.airportOrigin()));
        flight.setAirport_destination(airportMapper.toEntity(flightDto.airportDestination()));
        flight.setDuration(flightDto.duration());
        flight.setExitDate(flightDto.exitDate());
        flight.setExitTime(flightDto.exitTime());
        return flight;
    }
    public List<Flight> toEntities(List<FlightDto> flightDtos) {
        List<Flight> flights = new ArrayList<>();
        for (FlightDto flightDto : flightDtos) {
            flights.add(toEntity(flightDto));
        }
        return flights;
    }
    public FlightDto toDto(Flight flight) {
        return new FlightDto(airlineMapper.toDto(flight.getAirline()),airportMapper.toDto(flight.getAirport_origin()),airportMapper.toDto(flight.getAirport_destination()),flight.getExitDate(),flight.getExitTime(),flight.getDuration(),flight.getCapacity(),reserveMapper.toDtos(flight.getReserves()));
    }
    public List<FlightDto> toDtos(List<Flight> flights) {
        List<FlightDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            flightDtos.add(toDto(flight));
        }
        return flightDtos;
    }
}
