package model.service;

import model.entity.Airline;
import model.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportServices {
    Airport saveAirport(Airport airport);
    Optional<Airport> findAirportById(int id);
    Optional<Airport> updateAirport(int id, Airport airport);
    List<Airport> getAllAirports();
    void deleteAirport(int id);
}
