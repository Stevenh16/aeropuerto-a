package model.controller;

import model.entity.Flight;
import model.service.FlightServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private final FlightServices flightServices;
    public FlightController(FlightServices flightServices) {
        this.flightServices = flightServices;
    }
    @GetMapping()
    public ResponseEntity<List<Flight>> getFlights() {
        return ResponseEntity.ok(flightServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable int id) {
        return flightServices.findFlightById(id)
                .map(f->ResponseEntity.ok().body(f))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return createNewFlight(flight);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable int id, @RequestBody Flight flight) {
        Optional<Flight> flightUpdate = flightServices.updateFlight(id, flight);
        return flightUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()-> createNewFlight(flight));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable int id) {
        flightServices.deleteFlightById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<Flight> createNewFlight(Flight flight) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(flight.getId())
                .toUri();
        return ResponseEntity.created(location).body(flight);
    }

}
