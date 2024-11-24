package model.service;

import model.dto.AirportDto;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    AirportDto save(AirportDto airport);
    Optional<AirportDto> findById(Long id);
    Optional<AirportDto> update(Long id, AirportDto airport);
    List<AirportDto> findAll();
    List<AirportDto> findByName(String name);
    void deleteById(Long id);
}
