package model.mapper;

import model.dto.FlightDto;
import model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Named("complete")
    FlightDto toIdDto(Flight flight);
    @Named("listComplete")
    List<FlightDto> toListIdDto(List<Flight> flights);
    Flight toEntity(FlightDto flightDto);
    List<Flight> toListEntity(List<FlightDto> flightDtos);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    FlightDto toDto(Flight flight);
    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<FlightDto> toListDto(List<Flight> flights);
}
