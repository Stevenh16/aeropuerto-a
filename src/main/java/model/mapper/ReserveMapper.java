package model.mapper;

import model.dto.ReserveDto;
import model.entity.Reserve;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReserveMapper {
    @Named("complete")
    ReserveDto toIdDto(Reserve reserve);
    @Named("listComplete")
    List<ReserveDto> toListIdDto(List<Reserve> reserves);
    Reserve toEntity(ReserveDto reserveDto);
    List<Reserve> toListEntity(List<ReserveDto> reserves);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    ReserveDto toDto(Reserve reserve);
    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<ReserveDto> toListDto(List<Reserve> reserves);
}
