package model.controller;

import lombok.AllArgsConstructor;
import model.dto.AirportDto;
import model.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airports")
@AllArgsConstructor
public class AirportController {
    private final AirportService airportService;
    @GetMapping
    public ResponseEntity<List<AirportDto>> getAirports() {
        return ResponseEntity.ok(airportService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable("id") Long id) {
        return airportService.findById(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airport) {
        return createNewAirport(airport);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> updateAirport(@PathVariable Long id, @RequestBody AirportDto airport) {
        Optional<AirportDto> airportUpdated = airportService.update(id, airport);
        return airportUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewAirport(airport));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AirportDto> deleteAirport(@PathVariable Long id) {
        airportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<AirportDto> createNewAirport(AirportDto airport) {
        AirportDto airportIdDto = airportService.save(airport);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airportIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(airportIdDto);
    }
}
