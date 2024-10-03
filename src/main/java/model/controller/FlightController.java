package model.controller;

import lombok.AllArgsConstructor;
import model.dto.FlightDto;
import model.service.FlightServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
@AllArgsConstructor
public class FlightController {
    private final FlightServices flightServices;
    @GetMapping()
    public ResponseEntity<List<FlightDto>> getFlights() {
        return ResponseEntity.ok(flightServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable int id) {
        return flightServices.findById(id)
                .map(f->ResponseEntity.ok().body(f))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flight) {
        return createNewFlight(flight);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable int id, @RequestBody FlightDto flight) {
        Optional<FlightDto> flightUpdate = flightServices.update(id, flight);
        return flightUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()-> createNewFlight(flight));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDto> deleteFlight(@PathVariable int id) {
        flightServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<FlightDto> createNewFlight(FlightDto flight) {
        FlightDto flightIdDto = flightServices.save(flight);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(flightIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(flightIdDto);
    }

}
