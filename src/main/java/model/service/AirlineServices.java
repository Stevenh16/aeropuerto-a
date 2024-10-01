package model.service;

import model.dto.AirlineDto;
import model.dto.AirlineIdDto;

import java.util.List;
import java.util.Optional;

public interface AirlineServices {
    AirlineIdDto save(AirlineDto airline);
    Optional<AirlineIdDto> findById(int id);
    Optional<AirlineIdDto> update(int id, AirlineDto airline);
    List<AirlineIdDto> findAll();
    List<AirlineIdDto> findByName(String name);
    void deleteById(int id);
}
