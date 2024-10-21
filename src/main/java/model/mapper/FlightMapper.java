package model.mapper;

import model.dto.FlightDto;
import model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.context.annotation.Lazy;

import java.util.List;


@Mapper(componentModel = "spring", uses = {AirlineMapper.class, AirportMapper.class, ReserveMapper.class})
public interface FlightMapper {
    @Named("complete")
    @Mapping(source = "flight.airline",target = "airline", qualifiedByName = "completeWithoutFlight")
    @Mapping(source = "flight.airport_origin", target = "airportOrigin", qualifiedByName = "completeWithoutFlight")
    @Mapping(source = "flight.airport_destination", target = "airportDestination", qualifiedByName = "completeWithoutFlight")
    @Mapping(source = "flight.reserves", target = "reserves", qualifiedByName = "listCompleteWithoutEntities")
    FlightDto toIdDto(Flight flight);

    @Named("listComplete")
    @Mapping(source = "flight.airline",target = "airline", qualifiedByName = "completeWithoutFlight")
    @Mapping(source = "flight.airport_origin", target = "airportOrigin", qualifiedByName = "completeWithoutFlight")
    @Mapping(source = "flight.airport_destination", target = "airportDestination", qualifiedByName = "completeWithoutFlight")
    @Mapping(source = "flight.reserves", target = "reserves", qualifiedByName = "listCompleteWithoutEntities")
    List<FlightDto> toListIdDto(List<Flight> flights);

    @Mapping(source = "flightDto.airline",target = "airline", qualifiedByName = "entityWithoutFlight")
    @Mapping(source = "flightDto.airportOrigin", target = "airport_origin", qualifiedByName = "entityWithoutFlight")
    @Mapping(source = "flightDto.airportDestination", target = "airport_destination", qualifiedByName = "entityWithoutFlight")
    @Mapping(source = "flightDto.reserves", target = "reserves", qualifiedByName = "listEntityWithoutDtos")
    Flight toEntity(FlightDto flightDto);

    @Mapping(source = "flightDto.airline",target = "airline", qualifiedByName = "entityWithoutFlight")
    @Mapping(source = "flightDto.airportOrigin", target = "airport_origin", qualifiedByName = "entityWithoutFlight")
    @Mapping(source = "flightDto.airportDestination", target = "airport_destination", qualifiedByName = "entityWithoutFlight")
    @Mapping(source = "flightDto.reserves", target = "reserves", qualifiedByName = "listEntityWithoutDtos")
    List<Flight> toListEntity(List<FlightDto> flightDtos);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "flight.airline",target = "airline", qualifiedByName = "withoutIdWithoutFlight")
    @Mapping(source = "flight.airport_origin", target = "airportOrigin", qualifiedByName = "withoutIdWithoutFlight")
    @Mapping(source = "flight.airport_destination", target = "airportDestination", qualifiedByName = "withoutIdWithoutFlight")
    @Mapping(source = "flight.reserves", target = "reserves", qualifiedByName = "listWithoutIdWithoutEntities")
    FlightDto toDto(Flight flight);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "flight.airline",target = "airline", qualifiedByName = "withoutIdWithoutFlight")
    @Mapping(source = "flight.airport_origin", target = "airportOrigin", qualifiedByName = "withoutIdWithoutFlight")
    @Mapping(source = "flight.airport_destination", target = "airportDestination", qualifiedByName = "withoutIdWithoutFlight")
    @Mapping(source = "flight.reserves", target = "reserves", qualifiedByName = "listWithoutIdWithoutEntities")
    List<FlightDto> toListDto(List<Flight> flights);

    @Named("completeWithoutEntities")
    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "airportOrigin", ignore = true)
    @Mapping(target = "airportDestination", ignore = true)
    @Mapping(target = "reserves", ignore = true)
    FlightDto toIdDtoWithoutEntities(Flight flight);

    @Named("listCompleteWithoutEntities")
    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "airportOrigin", ignore = true)
    @Mapping(target = "airportDestination", ignore = true)
    @Mapping(target = "reserves", ignore = true)
    List<FlightDto> toListIdDtoWithoutEntities(List<Flight> flight);

    @Named("withoutIdWithoutEntities")
    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "airportOrigin", ignore = true)
    @Mapping(target = "airportDestination", ignore = true)
    @Mapping(target = "reserves", ignore = true)
    @Mapping(target = "id", ignore = true)
    FlightDto toDtoWithoutEntities(Flight flight);

    @Named("listWithoutIdWithoutEntities")
    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "airportOrigin", ignore = true)
    @Mapping(target = "airportDestination", ignore = true)
    @Mapping(target = "reserves", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<FlightDto> toListDtoWithoutEntities(List<Flight> flight);

    @Named("EntityWithoutDtos")
    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "airport_origin", ignore = true)
    @Mapping(target = "airport_destination", ignore = true)
    @Mapping(target = "reserves", ignore = true)
    Flight toEntityWithoutDtos(FlightDto flightDto);

    @Named("listEntityWithoutDtos")
    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "airportOrigin", ignore = true)
    @Mapping(target = "airportDestination", ignore = true)
    @Mapping(target = "reserves", ignore = true)
    List<Flight> toListEntityWithoutDtos(List<FlightDto> flightDtos);
}
