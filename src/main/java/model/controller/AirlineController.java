package model.controller;

import lombok.AllArgsConstructor;
import model.dto.AirlineDto;
import model.service.AirlineServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airlines")
@AllArgsConstructor
public class AirlineController {
    private final AirlineServices airlineServices;

    @GetMapping
    public ResponseEntity<List<AirlineDto>> getAirlines() {
        return ResponseEntity.ok(airlineServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getAirlineById(@PathVariable("id") int id) {
        return airlineServices.findById(id)
                .map( a-> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<AirlineDto> createAirline(@RequestBody AirlineDto airline) {
        return createNewAirline(airline);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AirlineDto> updateAirline(@PathVariable int id, @RequestBody AirlineDto airline){
        Optional<AirlineDto> airlineUpdated = airlineServices.update(id, airline);
        return airlineUpdated.map(ResponseEntity::ok).orElseGet(() -> createNewAirline(airline));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AirlineDto> deleteAirline(@PathVariable int id) {
        airlineServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AirlineDto> createNewAirline(AirlineDto airline) {
        AirlineDto airlineIdDto = airlineServices.save(airline);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airlineIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(airlineIdDto);
    }
}
