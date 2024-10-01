package model.mapper;

import lombok.AllArgsConstructor;
import model.dto.ClientDto;
import model.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ClientMapper {
    private ReserveMapper reserveMapper;
    public Client toEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.name());
        client.setLastname(clientDto.lastname());
        client.setEmail(clientDto.email());
        client.setAddress(clientDto.address());
        client.setCellphone(clientDto.cellphone());
        client.setReserves(reserveMapper.toEntities(clientDto.reserves()));
        return client;
    }
    public List<Client> toEntities(List<ClientDto> clientDtos) {
        List<Client> clients = new ArrayList<>();
        for (ClientDto clientDto : clientDtos) {
            clients.add(toEntity(clientDto));
        }
        return clients;
    }
    public ClientDto toDto(Client client) {
        return new ClientDto(client.getName(), client.getLastname(), client.getAddress(), client.getCellphone(), client.getEmail(), reserveMapper.toDtos(client.getReserves()));
    }
    public List<ClientDto> toDtos(List<Client> clients) {
        List<ClientDto> dtos = new ArrayList<>();
        for (Client client : clients) {
            dtos.add(toDto(client));
        }
        return dtos;
    }
}
