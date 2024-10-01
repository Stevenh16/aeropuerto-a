package model.service;

import model.dto.ClientDto;
import model.dto.ClientIdDto;

import java.util.List;
import java.util.Optional;

public interface ClientServices {
    ClientIdDto save(ClientDto client);
    Optional<ClientIdDto> findById(int id);
    Optional<ClientIdDto> update(int id, ClientDto client);
    List<ClientIdDto> findAll();
    List<ClientIdDto> findByName(String name);
    void deleteById(int id);
}
