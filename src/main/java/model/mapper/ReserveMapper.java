package model.mapper;

import model.dto.ReserveDto;
import model.entity.Reserve;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, PassengerMapper.class, FlightMapper.class})
public interface ReserveMapper {
    @Named("complete")
    @Mapping(source = "reserve.client", target = "client", qualifiedByName = "completeWithoutReserve")
    @Mapping(source = "reserve.passengers", target = "passengers", qualifiedByName = "listCompleteWithoutReserve")
    @Mapping(source = "reserve.flights", target = "flights", qualifiedByName = "listCompleteWithoutEntities")
    ReserveDto toIdDto(Reserve reserve);

    @Named("listComplete")
    @Mapping(source = "reserve.client", target = "client", qualifiedByName = "completeWithoutReserve")
    @Mapping(source = "reserve.passengers", target = "passengers", qualifiedByName = "listCompleteWithoutReserve")
    @Mapping(source = "reserve.flights", target = "flights", qualifiedByName = "listCompleteWithoutEntities")
    List<ReserveDto> toListIdDto(List<Reserve> reserves);

    @Mapping(source = "reserveDto.client", target = "client", qualifiedByName = "entityWithoutReserve")
    @Mapping(source = "reserveDto.passengers", target = "passengers", qualifiedByName = "listEntityWithoutReserve")
    @Mapping(source = "reserveDto.flights", target = "flights", qualifiedByName = "listEntityWithoutDtos")
    Reserve toEntity(ReserveDto reserveDto);

    @Mapping(source = "reserveDto.client", target = "client", qualifiedByName = "entityWithoutReserve")
    @Mapping(source = "reserveDto.passengers", target = "passengers", qualifiedByName = "listEntityWithoutReserve")
    @Mapping(source = "reserveDto.flights", target = "flights", qualifiedByName = "listEntityWithoutDtos")
    List<Reserve> toListEntity(List<ReserveDto> reserves);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "reserve.client", target = "client", qualifiedByName = "withoutIdWithoutReserve")
    @Mapping(source = "reserve.passengers", target = "passengers", qualifiedByName = "listWithoutIdWithoutReserve")
    @Mapping(source = "reserve.flights", target = "flights", qualifiedByName = "listWithoutIdWithoutEntities")
    ReserveDto toDto(Reserve reserve);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "reserve.client", target = "client", qualifiedByName = "completeWithoutReserve")
    @Mapping(source = "reserve.passengers", target = "passengers", qualifiedByName = "listCompleteWithoutReserve")
    @Mapping(source = "reserve.flights", target = "flights", qualifiedByName = "listWithoutIdWithoutEntities")
    List<ReserveDto> toListDto(List<Reserve> reserves);

    @Named("completeWithoutEntities")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flights", ignore = true)
    ReserveDto toIdDtoWithoutEntities(Reserve reserve);

    @Named("listCompleteWithoutEntities")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flights", ignore = true)
    List<ReserveDto> toListIdDtoWithoutEntities(List<Reserve> reserves);

    @Named("withoutIdWithoutEntities")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flights", ignore = true)
    @Mapping(target = "id", ignore = true)
    ReserveDto toDtoWithoutEntities(Reserve reserve);

    @Named("listWithoutIdWithoutEntities")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flights", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<ReserveDto> toListDtoWithoutEntities(List<Reserve> reserves);

    @Named("entityWithoutDtos")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flights", ignore = true)
    Reserve toEntityWithoutDtos(ReserveDto reserveDto);

    @Named("listEntityWithoutDtos")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flights", ignore = true)
    List<Reserve> toListEntityWithoutDtos(List<ReserveDto> reserves);
}
