package model.service;

import model.dto.AirlineDto;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    AirlineDto save(AirlineDto airline);
    Optional<AirlineDto> findById(Long id);
    Optional<AirlineDto> update(Long id, AirlineDto airline);
    List<AirlineDto> findAll();
    List<AirlineDto> findByName(String name);
    void deleteById(Long id);
}
