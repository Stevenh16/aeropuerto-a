package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.ClientDto;
import model.dto.ClientIdDto;
import model.entity.Client;
import model.mapper.ClientMapper;
import model.mapper.ReserveMapper;
import model.repository.ClientRepository;
import model.service.ClientServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientServices {
    private final ClientMapper clientMapper;
    private final ReserveMapper reserveMapper;
    private ClientRepository clientRepository;

    @Override
    public ClientIdDto save(ClientDto client) {
        return clientMapper.toIdDto(clientRepository.save(clientMapper.toEntity(client)));
    }

    @Override
    public Optional<ClientIdDto> findById(int id) {
        return Optional.of(clientMapper.toIdDto(clientRepository.findById(id).get()));
    }

    @Override
    public Optional<ClientIdDto> update(int id, ClientDto client) {
        return Optional.of(clientMapper.toIdDto(clientRepository.findById(id).map(oldClient -> {
            oldClient.setAddress(client.address());
            oldClient.setName(client.name());
            oldClient.setLastname(client.lastname());
            oldClient.setEmail(client.email());
            oldClient.setCellphone(client.cellphone());
            oldClient.setReserves(reserveMapper.toEntities(client.reserves()));
            return clientRepository.save(oldClient);
        }).get()));
    }

    @Override
    public List<ClientIdDto> findAll() {
        return clientMapper.toIdDtos(clientRepository.findAll());
    }

    @Override
    public List<ClientIdDto> findByName(String name) {
        Client c = new Client();
        c.setName(name);
        Example<Client> example = Example.of(c);
        return clientMapper.toIdDtos(clientRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}
