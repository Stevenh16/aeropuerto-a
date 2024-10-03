package model.mapper;

import model.dto.PassengerDto;
import model.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    @Named("complete")
    PassengerDto toIdDto(Passenger passenger);
    @Named("listComplete")
    List<PassengerDto> toListIdDto(List<Passenger> passengers);
    Passenger toEntity(PassengerDto passengerDto);
    List<Passenger> toListEntity(List<PassengerDto> passengerDto);
    @Named("withoutId")
    @Mapping(target="id",ignore = true)
    PassengerDto toDto(Passenger passenger);
    @Named("listWithoutId")
    @Mapping(target="id",ignore = true)
    List<PassengerDto> toListDto(List<Passenger> passengers);
}
