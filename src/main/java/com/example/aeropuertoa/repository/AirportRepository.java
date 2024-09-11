package com.example.aeropuertoa.repository;

import com.example.aeropuertoa.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
