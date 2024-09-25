package model.controller;

import model.entity.Airport;
import model.service.AirportServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {
    private final AirportServices airportServices;
    public AirportController(AirportServices airportServices) {
        this.airportServices = airportServices;
    }
    @GetMapping
    public ResponseEntity<List<Airport>> getAirports() {
        return ResponseEntity.ok(airportServices.getAllAirports());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable("id") int id) {
        return airportServices.findAirportById(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return createNewAirport(airport);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable int id, @RequestBody Airport airport) {
        Optional<Airport> airportUpdated = airportServices.updateAirport(id, airport);
        return airportUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewAirport(airport));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable int id) {
        airportServices.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<Airport> createNewAirport(Airport airport) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airport.getId())
                .toUri();
        return ResponseEntity.created(location).body(airport);
    }
}
