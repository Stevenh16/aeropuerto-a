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
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listComplete")
    AirlineDto toIdDto(Airline airline);

    @Mapping(source = "airlineDto.flights", target = "flights")
    Airline toEntity(AirlineDto airlineDto);

    @Named("listComplete")
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listComplete")
    List<AirlineDto> toListIdDto(List<Airline> airlines);

    @Mapping(source = "airlineDto.flights", target = "flights")
    List<Airline> toListEntity(List<AirlineDto> airlineDto);

    @Name("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listWithoutId")
    AirlineDto toDto(Airline airline);

    @Named("listWithoutId")
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "airline.flights", target = "flights", qualifiedByName = "listWithoutId")
    List<AirlineDto> toListDto(List<Airline> airlines);
}
