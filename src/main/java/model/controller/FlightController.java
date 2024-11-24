package model.controller;

import lombok.AllArgsConstructor;
import model.dto.FlightDto;
import model.service.FlightService;
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
    private final FlightService flightService;
    @GetMapping()
    public ResponseEntity<List<FlightDto>> getFlights() {
        return ResponseEntity.ok(flightService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        return flightService.findById(id)
                .map(f->ResponseEntity.ok().body(f))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flight) {
        return createNewFlight(flight);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @RequestBody FlightDto flight) {
        Optional<FlightDto> flightUpdate = flightService.update(id, flight);
        return flightUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()-> createNewFlight(flight));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDto> deleteFlight(@PathVariable Long id) {
        flightService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<FlightDto> createNewFlight(FlightDto flight) {
        FlightDto flightIdDto = flightService.save(flight);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(flightIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(flightIdDto);
    }

}
