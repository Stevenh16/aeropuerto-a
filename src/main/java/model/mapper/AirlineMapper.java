package model.mapper;

import jdk.jfr.Name;
import model.dto.AirlineDto;
import model.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring", uses = FlightMapper.class)
public interface AirlineMapper {
    @Named("complete")
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listCompleteWithoutEntities")
    AirlineDto toIdDto(Airline airline);

    @Mapping(source = "airlineDto.flights", target = "flights", qualifiedByName = "listEntityWithoutDtos")
    Airline toEntity(AirlineDto airlineDto);

    @Named("listComplete")
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listCompleteWithoutEntities")
    List<AirlineDto> toListIdDto(List<Airline> airlines);

    @Mapping(source = "airlineDto.flights", target = "flights", qualifiedByName = "listEntityWithoutDtos")
    List<Airline> toListEntity(List<AirlineDto> airlineDto);

    @Name("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listWithoutIdWithoutEntities")
    AirlineDto toDto(Airline airline);

    @Named("listWithoutId")
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listWithoutIdWithoutEntities")
    List<AirlineDto> toListDto(List<Airline> airlines);

    @Named("completeWithoutFlight")
    @Mapping(target = "flights", ignore = true)
    AirlineDto toIdDtoWithoutFlight(Airline airline);
    @Named("listCompleteWithoutFlight")
    @Mapping(target = "flights", ignore = true)
    List<AirlineDto> toListIdDtoWithoutFlight(List<Airline> airlines);

    @Named("entityWithoutFlight")
    @Mapping(target = "flights",ignore = true)
    Airline toEntityWithoutFlight(AirlineDto airlineDto);
    @Named("listEntityWithoutFlight")
    @Mapping(target = "flights", ignore = true)
    List<Airline> toListEntityWithoutFlight(List<AirlineDto> airlineDto);

    @Named("withoutIdWithoutFlight")
    @Mapping(target = "flights", ignore = true)
    @Mapping(target = "id",ignore = true)
    AirlineDto toDtoWithoutFlight(Airline airline);
    @Named("listWithoutIdWithoutFlight")
    @Mapping(target = "flights", ignore = true)
    @Mapping(target = "id",ignore = true)
    List<AirlineDto> toListDtoWithoutFlight(List<Airline> airlines);
}
