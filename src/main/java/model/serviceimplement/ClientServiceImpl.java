package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.entity.Client;
import model.repository.ClientRepository;
import model.service.ClientServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientServices {
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findClientById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<Client> updateClient(int id, Client client) {
        Client c = clientRepository.findById(id).get();
        c.setAddress(client.getAddress());
        c.setName(client.getName());
        c.setCellphone(client.getCellphone());
        c.setEmail(client.getEmail());
        c.setLastname(client.getLastname());
        c.setReserves(client.getReserves());
        return Optional.of(clientRepository.save(c));
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findByName(String name) {
        Client c = new Client();
        c.setName(name);
        Example<Client> example = Example.of(c);
        return clientRepository.findAll(example);
    }

    @Override
    public void deleteClientById(int id) {
        clientRepository.deleteById(id);
    }
}
