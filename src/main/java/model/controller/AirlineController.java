package model.controller;

import lombok.AllArgsConstructor;
import model.dto.AirlineDto;
import model.service.AirlineService;
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
    private final AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<AirlineDto>> getAirlines() {
        return ResponseEntity.ok(airlineService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getAirlineById(@PathVariable("id") Long id) {
        return airlineService.findById(id)
                .map( a-> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<AirlineDto> createAirline(@RequestBody AirlineDto airline) {
        return createNewAirline(airline);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AirlineDto> updateAirline(@PathVariable Long id, @RequestBody AirlineDto airline){
        Optional<AirlineDto> airlineUpdated = airlineService.update(id, airline);
        return airlineUpdated.map(ResponseEntity::ok).orElseGet(() -> createNewAirline(airline));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AirlineDto> deleteAirline(@PathVariable Long id) {
        airlineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AirlineDto> createNewAirline(AirlineDto airline) {
        AirlineDto airlineIdDto = airlineService.save(airline);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(airlineIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(airlineIdDto);
    }
}
