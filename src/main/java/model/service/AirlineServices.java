package model.service;

import model.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineServices {
    Airline saveAirline(Airline airline);
    Optional<Airline> findAirlineById(int id);
    Optional<Airline> updateAirline(int id, Airline airline);
    List<Airline> findAll();
    List<Airline> findByName(String name);
    void deleteById(int id);
}
