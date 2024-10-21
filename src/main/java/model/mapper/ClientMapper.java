package model.mapper;

import model.dto.ClientDto;
import model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReserveMapper.class)
public interface ClientMapper {
    @Named("complete")
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listCompleteWithoutEntities")
    ClientDto toIdDto(Client client);

    @Named("listComplete")
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listCompleteWithoutEntities")
    List<ClientDto> toListIdDto(List<Client> clients);

    @Mapping(source = "clientDto.reserves", target = "reserves", qualifiedByName = "listEntityWithoutDtos")
    Client toEntity(ClientDto clientDto);


    @Mapping(source = "clientDto.reserves", target = "reserves", qualifiedByName = "listEntityWithoutDtos")
    List<Client> toListEntity(List<ClientDto> clientDto);

    @Named("withoutId")
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listWithoutIdWithoutEntities")
    ClientDto toDto(Client client);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listWithoutWithoutEntities")
    List<ClientDto> toListDto(List<Client> clients);

    @Named("completeWithoutReserve")
    @Mapping(target = "reserves", ignore = true)
    ClientDto toIdDtoWithoutReserve(Client client);
    @Named("listCompleteWithoutReserve")
    @Mapping(target = "reserves", ignore = true)
    List<ClientDto> toListIdDtoWithoutReserve(List<Client> clients);

    @Named("entityWithoutReserve")
    @Mapping(target = "reserves", ignore = true)
    Client toEntityWithoutReserve(Client client);
    @Named("listEntityWithoutReserve")
    @Mapping(target = "reserves", ignore = true)
    List<Client> toListEntityWithoutReserve(List<Client> clients);

    @Named("withoutIdWithoutReserve")
    @Mapping(target = "reserves", ignore = true)
    @Mapping(target = "id", ignore = true)
    ClientDto toDtoWithoutReserve(Client client);
    @Named("listWithoutIdWithoutReserve")
    @Mapping(target = "reserves", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<ClientDto> toListDtoWithoutReserve(List<Client> clients);
}
