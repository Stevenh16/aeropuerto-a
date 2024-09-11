package com.example.aeropuertoa.service;

import com.example.aeropuertoa.entity.Airline;
import com.example.aeropuertoa.repository.AirlineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirlineService {
    private AirlineRepository airlineRepository;


    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(int id) {
        return airlineRepository.findById(id).get();
    }

    public Airline updateAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public void deleteAirlineById(int id) {
        airlineRepository.deleteById(id);
    }
}
