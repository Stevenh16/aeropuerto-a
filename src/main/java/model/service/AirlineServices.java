package model.service;

import model.entity.Airline;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AirlineServices {
    Airline saveAirline(Airline airline);
    Optional<Airline> findAirlineById(int id);
    Optional<Airline> updateAirline(int id, Airline airline);
    List<Airline> findAll();
    List<Airline> findByName(String name);
    void deleteById(int id);
}
