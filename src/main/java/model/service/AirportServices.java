package model.service;

import model.entity.Airport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AirportServices {
    Airport saveAirport(Airport airport);
    Optional<Airport> findAirportById(int id);
    Optional<Airport> updateAirport(int id, Airport airport);
    List<Airport> getAllAirports();
    List<Airport> findByName(String name);
    void deleteAirport(int id);
}
