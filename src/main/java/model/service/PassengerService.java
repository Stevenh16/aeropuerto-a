package model.service;

import model.dto.PassengerDto;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    PassengerDto save(PassengerDto passenger);
    Optional<PassengerDto> getById(Long id);
    Optional<PassengerDto> update(Long id, PassengerDto passenger);
    List<PassengerDto> findAll();
    List<PassengerDto> findByName(String name);
    void deleteById(Long id);
}
