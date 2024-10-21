package model.mapper;

import model.dto.AirportDto;
import model.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = FlightMapper.class)
public interface AirportMapper {
    @Named("complete")
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listCompleteWithoutEntities")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listCompleteWithoutEntities")
    AirportDto toIdDto(Airport airport);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listWithoutIdWithoutEntities")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listWithoutIdWithoutEntities")
    AirportDto toDto(Airport airport);

    @Named("listComplete")
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listCompleteWithoutEntities")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listCompleteWithoutEntities")
    List<AirportDto> toListIdDto(List<Airport> airports);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listWithoutIdWithoutEntities")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listWithoutIdWithoutEntities")
    List<AirportDto> toListDto(List<Airport> airports);

    @Mapping(source = "airportDto.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listEntityWithoutDtos")
    @Mapping(source = "airportDto.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listEntityWithoutDtos")
    Airport toEntity(AirportDto airportDto);

    @Mapping(source = "airportDto.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listEntityWithoutDtos")
    @Mapping(source = "airportDto.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listEntityWithoutDtos")
    List<Airport> toListEntity(List<AirportDto> airportDtos);

    @Named("completeWithoutFlight")
    @Mapping(target  = "flightsOrigins", ignore = true)
    @Mapping(target = "flightsDestinations", ignore = true)
    AirportDto toIdDtoWithoutFlight(Airport airport);
    @Named("listCompleteWithoutFlight")
    @Mapping(target  = "flightsOrigins", ignore = true)
    @Mapping(target = "flightsDestinations", ignore = true)
    List<AirportDto> toListIdDtoWithoutFlight(List<Airport> airports);

    @Named("withoutIdWithoutFlight")
    @Mapping(target = "id", ignore = true)
    @Mapping(target  = "flightsOrigins", ignore = true)
    @Mapping(target = "flightsDestinations", ignore = true)
    AirportDto toDtoWithoutFlight(Airport airport);
    @Named("listWithoutIdWithoutFlight")
    @Mapping(target = "id", ignore = true)
    @Mapping(target  = "flightsOrigins", ignore = true)
    @Mapping(target = "flightsDestinations", ignore = true)
    List<AirportDto> toListDtoWithoutFlight(List<Airport> airports);

    @Named("entityWithoutFlight")
    @Mapping(target  = "flightsOrigins", ignore = true)
    @Mapping(target = "flightsDestinations", ignore = true)
    Airport toEntityWithoutFlight(AirportDto airportDto);
    @Named("listEntityWithoutFlight")
    @Mapping(target  = "flightsOrigins", ignore = true)
    @Mapping(target = "flightsDestinations", ignore = true)
    List<Airport> toListEntityWithoutFlight(List<AirportDto> airportDtos);
}