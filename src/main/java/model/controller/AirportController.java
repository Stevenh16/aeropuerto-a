package model.controller;

import model.dto.AirportDto;
import model.dto.AirportIdDto;
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
    public ResponseEntity<List<AirportIdDto>> getAirports() {
        return ResponseEntity.ok(airportServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirportIdDto> getAirportById(@PathVariable("id") int id) {
        return airportServices.findById(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<AirportIdDto> createAirport(@RequestBody AirportDto airport) {
        return createNewAirport(airport);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AirportIdDto> updateAirport(@PathVariable int id, @RequestBody AirportDto airport) {
        Optional<AirportIdDto> airportUpdated = airportServices.update(id, airport);
        return airportUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewAirport(airport));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AirportIdDto> deleteAirport(@PathVariable int id) {
        airportServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<AirportIdDto> createNewAirport(AirportDto airport) {
        AirportIdDto airportIdDto = airportServices.save(airport);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airportIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(airportIdDto);
    }
}
