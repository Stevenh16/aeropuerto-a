package model.mapper;

import jdk.jfr.Name;
import model.dto.AirlineDto;
import model.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    @Named("complete")
    AirlineDto toIdDto(Airline airline);
    Airline toEntity(AirlineDto airlineDto);
    @Named("listComplete")
    List<AirlineDto> toListIdDto(List<Airline> airlines);
    List<Airline> toListEntity(List<AirlineDto> airlineDto);
    @Name("withoutId")
    @Mapping(target = "id", ignore = true)
    AirlineDto toDto(Airline airline);
    @Named("listWithoutId")
    @Mapping(target = "id",ignore = true)
    List<AirlineDto> toListDto(List<Airline> airlines);
}
