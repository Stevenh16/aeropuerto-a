package model.service;

import model.entity.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface FlightServices {
    Flight saveFlight(Flight flight);
    Optional<Flight> findFlightById(int id);
    Optional<Flight> updateFlight(int id, Flight flight);
    List<Flight> findAll();
    List<Flight> findByDate(LocalDate date);
    void deleteFlightById(int id);
}
