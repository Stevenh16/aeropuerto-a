package model.service;

import model.dto.FlightDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDto save(FlightDto flight);
    Optional<FlightDto> findById(Long id);
    Optional<FlightDto> update(Long id, FlightDto flight);
    List<FlightDto> findAll();
    List<FlightDto> findByDate(LocalDate date);
    void deleteById(Long id);
}
