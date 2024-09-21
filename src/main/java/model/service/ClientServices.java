package model.service;

import model.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientServices {
    Client saveClient(Client client);
    Optional<Client> findClientById(int id);
    Optional<Client> updateClient(int id, Client client);
    List<Client> findAllClients();
    List<Client> findByName(String name);
    void deleteClientById(int id);
}
