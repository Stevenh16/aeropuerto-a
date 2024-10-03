package model.mapper;

import model.dto.AirportDto;
import model.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    @Named("complete")
    AirportDto toIdDto(Airport airport);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    AirportDto toDto(Airport airport);
    @Named("listComplete")
    List<AirportDto> toListIdDto(List<Airport> airports);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<AirportDto> toListDto(List<Airport> airports);

    Airport toEntity(AirportDto airportDto);
    List<Airport> toListEntity(List<AirportDto> airportDtos);
}