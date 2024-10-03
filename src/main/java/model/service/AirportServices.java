package model.service;

import model.dto.AirportDto;

import java.util.List;
import java.util.Optional;

public interface AirportServices {
    AirportDto save(AirportDto airport);
    Optional<AirportDto> findById(int id);
    Optional<AirportDto> update(int id, AirportDto airport);
    List<AirportDto> findAll();
    List<AirportDto> findByName(String name);
    void deleteById(int id);
}
