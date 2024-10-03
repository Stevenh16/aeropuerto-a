package model.service;

import model.dto.FlightDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightServices {
    FlightDto save(FlightDto flight);
    Optional<FlightDto> findById(int id);
    Optional<FlightDto> update(int id, FlightDto flight);
    List<FlightDto> findAll();
    List<FlightDto> findByDate(LocalDate date);
    void deleteById(int id);
}
