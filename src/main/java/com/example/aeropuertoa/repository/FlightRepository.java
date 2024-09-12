package com.example.aeropuertoa.repository;

import com.example.aeropuertoa.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
