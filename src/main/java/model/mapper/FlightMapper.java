package model.mapper;

import model.dto.FlightDto;
import model.entity.*;
import model.repository.AirlineRepository;
import model.repository.AirportRepository;
import model.repository.ClientRepository;
import model.repository.ReserveRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class FlightMapper {
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReserveRepository reserveRepository;

    @Named("complete")
    @Mapping(source = "flight.airline.id",target = "airline")
    @Mapping(source = "flight.airportOrigin.id", target = "airportOrigin")
    @Mapping(source = "flight.airportDestination.id", target = "airportDestination")
    @Mapping(source = "flight.reserves", target = "reserves")
    public abstract FlightDto toIdDto(Flight flight);

    @Named("listComplete")
    @Mapping(source = "flight.airline.id",target = "airline")
    @Mapping(source = "flight.airportOrigin.id", target = "airportOrigin")
    @Mapping(source = "flight.airportDestination.id", target = "airportDestination")
    @Mapping(source = "flight.reserves", target = "reserves")
    public abstract List<FlightDto> toListIdDto(List<Flight> flights);

    @Mapping(source = "flightDto.airline",target = "airline")
    @Mapping(source = "flightDto.airportOrigin", target = "airportOrigin")
    @Mapping(source = "flightDto.airportDestination", target = "airportDestination")
    @Mapping(source = "flightDto.reserves", target = "reserves")
    public abstract Flight toEntity(FlightDto flightDto);

    @Mapping(source = "flightDto.airline",target = "airline")
    @Mapping(source = "flightDto.airportOrigin", target = "airportOrigin")
    @Mapping(source = "flightDto.airportDestination", target = "airportDestination")
    @Mapping(source = "flightDto.reserves", target = "reserves")
    public abstract List<Flight> toListEntity(List<FlightDto> flightDtos);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "flight.airline.id",target = "airline")
    @Mapping(source = "flight.airportOrigin.id", target = "airportOrigin")
    @Mapping(source = "flight.airportDestination.id", target = "airportDestination")
    @Mapping(source = "flight.reserves", target = "reserves")
    public abstract FlightDto toDto(Flight flight);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "flight.airline.id",target = "airline")
    @Mapping(source = "flight.airportOrigin.id", target = "airportOrigin")
    @Mapping(source = "flight.airportDestination.id", target = "airportDestination")
    @Mapping(source = "flight.reserves", target = "reserves")
    public abstract List<FlightDto> toListDto(List<Flight> flights);

    public Long mapToClientId(Client client){
        return client.getId();
    }

    public Client mapToClient(Long id){
        return clientRepository.findById(id).get();
    }

    public Long mapToAirlineId(Airline airline){
        return airline.getId();
    }

    public Airline mapToAirline(Long id){
        return airlineRepository.findById(id).get();
    }

    public Long mapToAirportId(Airport airport){
        return airport.getId();
    }

    public Airport mapToAirport(Long id){
        return airportRepository.findById(id).get();
    }

    public Long mapToReserveId(Reserve reserve){
        return reserve.getId();
    }
    public Reserve mapToReserve(Long id){
        return reserveRepository.findById(id).get();
    }

    public List<Reserve> mapToReserveList(List<Long> ids){
        List<Reserve> reserves = new ArrayList<Reserve>();
        for(Long id : ids){
            reserves.add(mapToReserve(id));
        }
        return reserves;
    }
}
