package model.controller;

import lombok.AllArgsConstructor;
import model.dto.AirportDto;
import model.service.AirportServices;
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
    private final AirportServices airportServices;
    @GetMapping
    public ResponseEntity<List<AirportDto>> getAirports() {
        return ResponseEntity.ok(airportServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable("id") int id) {
        return airportServices.findById(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airport) {
        return createNewAirport(airport);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> updateAirport(@PathVariable int id, @RequestBody AirportDto airport) {
        Optional<AirportDto> airportUpdated = airportServices.update(id, airport);
        return airportUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewAirport(airport));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AirportDto> deleteAirport(@PathVariable int id) {
        airportServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<AirportDto> createNewAirport(AirportDto airport) {
        AirportDto airportIdDto = airportServices.save(airport);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airportIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(airportIdDto);
    }
}
