package model.service;

import model.dto.PassengerDto;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    PassengerDto save(PassengerDto passenger);
    Optional<PassengerDto> getById(int id);
    Optional<PassengerDto> update(int id, PassengerDto passenger);
    List<PassengerDto> findAll();
    List<PassengerDto> findByName(String name);
    void deleteById(int id);
}
