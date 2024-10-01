package model.service;

import model.dto.AirportDto;
import model.dto.AirportIdDto;

import java.util.List;
import java.util.Optional;

public interface AirportServices {
    AirportIdDto save(AirportDto airport);
    Optional<AirportIdDto> findById(int id);
    Optional<AirportIdDto> update(int id, AirportDto airport);
    List<AirportIdDto> findAll();
    List<AirportIdDto> findByName(String name);
    void deleteById(int id);
}
