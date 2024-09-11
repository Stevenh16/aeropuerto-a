package com.example.aeropuertoa.service;

import com.example.aeropuertoa.entity.Airport;
import com.example.aeropuertoa.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {
    private AirportRepository airportRepository;

    private Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
    public Airport getAirportById(int id) {
        return airportRepository.findById((long) id).get();
    }
    public Airport updateAirport(Airport airport) {
        return airportRepository.save(airport);
    }
    public void deleteAirportById(int id) {
        airportRepository.deleteById((long) id);
    }
}
