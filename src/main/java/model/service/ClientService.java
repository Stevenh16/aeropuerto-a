package model.service;

import model.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDto save(ClientDto client);
    Optional<ClientDto> findById(Long id);
    Optional<ClientDto> update(Long id, ClientDto client);
    List<ClientDto> findAll();
    List<ClientDto> findByName(String name);
    void deleteById(Long id);
}
