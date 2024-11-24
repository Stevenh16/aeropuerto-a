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
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listComplete")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listComplete")
    AirportDto toIdDto(Airport airport);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listWithoutId")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listWithoutId")
    AirportDto toDto(Airport airport);

    @Named("listComplete")
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listComplete")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listComplete")
    List<AirportDto> toListIdDto(List<Airport> airports);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airport.flightsOrigins", target = "flightsOrigins", qualifiedByName = "listWithoutId")
    @Mapping(source = "airport.flightsDestinations", target = "flightsDestinations", qualifiedByName = "listWithoutId")
    List<AirportDto> toListDto(List<Airport> airports);

    @Mapping(source = "airportDto.flightsOrigins", target = "flightsOrigins")
    @Mapping(source = "airportDto.flightsDestinations", target = "flightsDestinations")
    Airport toEntity(AirportDto airportDto);

    @Mapping(source = "airportDto.flightsOrigins", target = "flightsOrigins")
    @Mapping(source = "airportDto.flightsDestinations", target = "flightsDestinations")
    List<Airport> toListEntity(List<AirportDto> airportDtos);
}