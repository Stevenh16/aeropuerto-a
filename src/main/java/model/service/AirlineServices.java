package model.service;

import model.dto.AirlineDto;

import java.util.List;
import java.util.Optional;

public interface AirlineServices {
    AirlineDto save(AirlineDto airline);
    Optional<AirlineDto> findById(int id);
    Optional<AirlineDto> update(int id, AirlineDto airline);
    List<AirlineDto> findAll();
    List<AirlineDto> findByName(String name);
    void deleteById(int id);
}
