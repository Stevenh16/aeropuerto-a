package model.mapper;

import model.dto.ClientDto;
import model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Named("complete")
    ClientDto toIdDto(Client client);
    @Named("listComplete")
    List<ClientDto> toListIdDto(List<Client> clients);
    Client toEntity(ClientDto clientDto);
    List<Client> toListEntity(List<ClientDto> clientDto);
    @Named("withoutId")
    @Mapping(target = "id",ignore = true)
    ClientDto toDto(Client client);
    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<ClientDto> toListDto(List<Client> clients);
}
