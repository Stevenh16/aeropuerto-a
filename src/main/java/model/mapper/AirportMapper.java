package model.mapper;

import lombok.AllArgsConstructor;
import model.dto.AirportDto;
import model.dto.AirportIdDto;
import model.entity.Airport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AirportMapper {
    private FlightMapper flightMapper;
    public Airport toEntity(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setName(airportDto.name());
        airport.setCity(airportDto.city());
        airport.setCountry(airportDto.country());
        airport.setFlightsOrigins(flightMapper.toEntities(airportDto.flightsOrigins()));
        airport.setFlightsDestinations(flightMapper.toEntities(airportDto.flightsDestinations()));
        return airport;
    }
    public List<Airport> toEntities(List<AirportDto> airportDtos) {
        List<Airport> airports = new ArrayList<>();
        for (AirportDto airportDto : airportDtos) {
            airports.add(toEntity(airportDto));
        }
        return airports;
    }
    public AirportDto toDto(Airport airport) {
        return new AirportDto(airport.getName(), airport.getCity(), airport.getCountry(), flightMapper.toDtos(airport.getFlightsOrigins()) , flightMapper.toDtos(airport.getFlightsDestinations()) );
    }
    public List<AirportDto> toDtos(List<Airport> airports) {
        List<AirportDto> airportDtos = new ArrayList<>();
        for (Airport airport : airports) {
            airportDtos.add(toDto(airport));
        }
        return airportDtos;
    }
    public AirportIdDto toIdDto(Airport airport) {
        return new AirportIdDto(airport.getId(), airport.getName(), airport.getCity(), airport.getCountry(),flightMapper.toIdDtos(airport.getFlightsOrigins()),flightMapper.toIdDtos(airport.getFlightsDestinations()));
    }
    public List<AirportIdDto> toIdDtos(List<Airport> airports) {
        List<AirportIdDto> airportIdDtos = new ArrayList<>();
        for (Airport airport : airports) {
            airportIdDtos.add(toIdDto(airport));
        }
        return airportIdDtos;
    }
}
