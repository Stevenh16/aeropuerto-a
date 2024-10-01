package model.service;

import model.dto.PassengerDto;
import model.dto.PassengerIdDto;

import java.util.List;
import java.util.Optional;

public interface PassengerServices {
    PassengerIdDto save(PassengerDto passenger);
    Optional<PassengerIdDto> getById(int id);
    Optional<PassengerIdDto> update(int id, PassengerDto passenger);
    List<PassengerIdDto> findAll();
    List<PassengerIdDto> findByName(String name);
    void deleteById(int id);
}
