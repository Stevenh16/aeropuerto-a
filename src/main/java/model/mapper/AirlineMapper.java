package model.mapper;

import lombok.AllArgsConstructor;
import model.dto.AirlineDto;
import model.dto.AirlineIdDto;
import model.entity.Airline;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AirlineMapper {
    private FlightMapper flightMapper;
    public Airline toEntity(AirlineDto airline) {
        Airline airlineEntity = new Airline();
        airlineEntity.setName(airline.name());
        airlineEntity.setAirlineCode(airline.airlineCode());
        airlineEntity.setFlights(flightMapper.toEntities(airline.flights()));
        airlineEntity.setCountryOfOrigin(airline.countryOfOrigin());
        return airlineEntity;
    }
    public List<Airline> toEntities(List<AirlineDto> airlineDtos) {
        List<Airline> airlineEntities = new ArrayList<>();
        for(AirlineDto airlineDto : airlineDtos) {
            airlineEntities.add(toEntity(airlineDto));
        }
        return airlineEntities;
    }
    public AirlineDto toDto(Airline airlineEntity) {
        return new AirlineDto(airlineEntity.getName(),airlineEntity.getAirlineCode(),airlineEntity.getCountryOfOrigin(),flightMapper.toDtos(airlineEntity.getFlights()));
    }
    public List<AirlineDto> toDtos(List<Airline> airlineEntities) {
        List<AirlineDto> airlineDtos = new ArrayList<>();
        for(Airline airlineEntity : airlineEntities) {
            airlineDtos.add(toDto(airlineEntity));
        }
        return airlineDtos;
    }
    public AirlineIdDto toIdDto(Airline airlineEntity) {
        return new AirlineIdDto(airlineEntity.getId(),airlineEntity.getName(), airlineEntity.getAirlineCode(), airlineEntity.getCountryOfOrigin(), flightMapper.toIdDtos(airlineEntity.getFlights()));
    }
    public List<AirlineIdDto> toIdDtos(List<Airline> airlineEntities) {
        List<AirlineIdDto> airlineIdDtos = new ArrayList<>();
        for(Airline airlineEntity : airlineEntities) {
            airlineIdDtos.add(toIdDto(airlineEntity));
        }
        return airlineIdDtos;
    }
}
