package com.example.aeropuertoa.repository;

import com.example.aeropuertoa.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository <Airline, Integer> {

}
