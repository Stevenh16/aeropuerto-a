package model.service;

import model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServices {
    Client saveClient(Client client);
    Optional<Client> findClientById(int id);
    Optional<Client> updateClient(int id, Client client);
    List<Client> findAllClients();
    void deleteClientById(int id);
}
