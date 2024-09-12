package com.example.aeropuertoa.service;

import com.example.aeropuertoa.entity.Client;
import com.example.aeropuertoa.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    private Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    public Client getClientById(int id) {
        return clientRepository.findById(id).get();
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public void deleteClientById(int id) {
        clientRepository.deleteById(id);
    }
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
}
