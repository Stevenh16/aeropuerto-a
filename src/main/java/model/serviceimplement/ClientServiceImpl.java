package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.ClientDto;
import model.entity.Client;
import model.mapper.ClientMapper;
import model.mapper.ReserveMapper;
import model.repository.ClientRepository;
import model.service.ClientService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ReserveMapper reserveMapper;
    private ClientRepository clientRepository;

    @Override
    public ClientDto save(ClientDto client) {
        return clientMapper.toIdDto(clientRepository.save(clientMapper.toEntity(client)));
    }

    @Override
    public Optional<ClientDto> findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toIdDto);
    }

    @Override
    public Optional<ClientDto> update(Long id, ClientDto client) {
        return clientRepository.findById(id).map(oldClient -> {
            oldClient.setAddress(client.address());
            oldClient.setName(client.name());
            oldClient.setLastname(client.lastname());
            oldClient.setEmail(client.email());
            oldClient.setCellphone(client.cellphone());
            oldClient.setReserves(reserveMapper.toListEntity(client.reserves()));
            return clientMapper.toIdDto(clientRepository.save(oldClient));
        });
    }

    @Override
    public List<ClientDto> findAll() {
        return clientMapper.toListIdDto(clientRepository.findAll());
    }

    @Override
    public List<ClientDto> findByName(String name) {
        Client c = new Client();
        c.setName(name);
        Example<Client> example = Example.of(c);
        return clientMapper.toListIdDto(clientRepository.findAll(example));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
