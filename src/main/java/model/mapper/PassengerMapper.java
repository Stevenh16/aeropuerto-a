package model.mapper;

import model.dto.PassengerDto;
import model.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReserveMapper.class)
public interface PassengerMapper {
    @Named("complete")
    @Mapping(source = "passenger.reserve", target = "reserve", qualifiedByName = "completeWithoutEntities")
    PassengerDto toIdDto(Passenger passenger);

    @Named("listComplete")
    @Mapping(source = "passenger.reserve", target = "reserve", qualifiedByName = "completeWithoutEntities")
    List<PassengerDto> toListIdDto(List<Passenger> passengers);

    @Mapping(source = "passengerDto.reserve", target = "reserve", qualifiedByName = "entityWithoutDtos")
    Passenger toEntity(PassengerDto passengerDto);


    @Mapping(source = "passengerDto.reserve", target = "reserve", qualifiedByName = "entityWithoutDtos")
    List<Passenger> toListEntity(List<PassengerDto> passengerDto);

    @Named("withoutId")
    @Mapping(target="id",ignore = true)
    @Mapping(source = "passenger.reserve", target = "reserve", qualifiedByName = "withoutIdWithoutEntities")
    PassengerDto toDto(Passenger passenger);

    @Named("listWithoutId")
    @Mapping(target="id",ignore = true)
    @Mapping(source = "passenger.reserve", target = "reserve", qualifiedByName = "withoutIdWithoutEntities")
    List<PassengerDto> toListDto(List<Passenger> passengers);

    @Named("completeWithoutReserve")
    @Mapping(target = "reserve", ignore = true)
    PassengerDto toIdDtoWithoutReserve(Passenger passenger);

    @Named("listCompleteWithoutReserve")
    @Mapping(target = "reserve", ignore = true)
    List<PassengerDto> toListIdDtoWithoutReserve(List<Passenger> passengers);

    @Named("entityWithoutReserve")
    @Mapping(target = "reserve", ignore = true)
    Passenger toEntityWithoutReserve(Passenger passenger);

    @Named("listEntityWithoutReserve")
    @Mapping(target = "reserve", ignore = true)
    List<Passenger> toListEntityWithoutReserve(List<Passenger> passengers);

    @Named("withoutIdWithoutReserve")
    @Mapping(target = "reserve", ignore = true)
    @Mapping(target = "id", ignore = true)
    PassengerDto toDtoWithoutReserve(Passenger passenger);

    @Named("listWithoutIdWithoutReserve")
    @Mapping(target = "reserve", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<PassengerDto> toListDtoWithoutReserve(List<Passenger> passengers);
}
