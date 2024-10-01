package model.service;

import model.dto.FlightDto;
import model.dto.FlightIdDto;
import model.entity.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightServices {
    FlightIdDto save(FlightDto flight);
    Optional<FlightIdDto> findById(int id);
    Optional<FlightIdDto> update(int id, FlightDto flight);
    List<FlightIdDto> findAll();
    List<FlightIdDto> findByDate(LocalDate date);
    void deleteById(int id);
}
