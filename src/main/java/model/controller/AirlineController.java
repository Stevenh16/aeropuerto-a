package model.controller;

import model.entity.Airline;
import model.service.AirlineServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlineController {
    private final AirlineServices airlineServices;

    public AirlineController(AirlineServices airlineServices) {
        this.airlineServices = airlineServices;
    }
    @GetMapping
    public ResponseEntity<List<Airline>> getAirlines() {
        return ResponseEntity.ok(airlineServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable("id") int id) {
        return airlineServices.findAirlineById(id)
                .map( a-> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        return createNewAirline(airline);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable int id, @RequestBody Airline airline){
        Optional<Airline> airlineUpdated = airlineServices.updateAirline(id, airline);
        return airlineUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewAirline(airline));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Airline> deleteAirline(@PathVariable int id) {
        airlineServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Airline> createNewAirline(Airline airline) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airline.getId())
                .toUri();
        return ResponseEntity.created(location).body(airline);
    }
}
