package model.controller;

import model.dto.FlightDto;
import model.dto.FlightIdDto;
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
    public ResponseEntity<List<FlightIdDto>> getFlights() {
        return ResponseEntity.ok(flightServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightIdDto> getFlightById(@PathVariable int id) {
        return flightServices.findById(id)
                .map(f->ResponseEntity.ok().body(f))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<FlightIdDto> createFlight(@RequestBody FlightDto flight) {
        return createNewFlight(flight);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlightIdDto> updateFlight(@PathVariable int id, @RequestBody FlightDto flight) {
        Optional<FlightIdDto> flightUpdate = flightServices.update(id, flight);
        return flightUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()-> createNewFlight(flight));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<FlightIdDto> deleteFlight(@PathVariable int id) {
        flightServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<FlightIdDto> createNewFlight(FlightDto flight) {
        FlightIdDto flightIdDto = flightServices.save(flight);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(flightIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(flightIdDto);
    }

}
