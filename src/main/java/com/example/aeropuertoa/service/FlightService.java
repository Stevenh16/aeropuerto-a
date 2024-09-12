package com.example.aeropuertoa.service;

import com.example.aeropuertoa.entity.Flight;
import com.example.aeropuertoa.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;

    private Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    public Flight getFlightById(int id) {
        return flightRepository.findById(id).get();
    }
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    public void deleteFlightById(int id) {
        flightRepository.deleteById(id);
    }
}
