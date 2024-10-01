package model.controller;

import model.dto.PassengerDto;
import model.dto.PassengerIdDto;
import model.service.PassengerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {
    private final PassengerServices passengerServices;
    public PassengerController(PassengerServices passengerServices) {
        this.passengerServices = passengerServices;
    }
    @GetMapping()
    public ResponseEntity<List<PassengerIdDto>> getPassengers() {
        return ResponseEntity.ok(passengerServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PassengerIdDto> getPassengerById(@PathVariable("id") int id) {
        return passengerServices.getById(id)
                .map(p->ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<PassengerIdDto> createPassenger(@RequestBody PassengerDto passenger) {
        return createNewPassenger(passenger);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PassengerIdDto> updatePassenger(@PathVariable("id") int id,@RequestBody PassengerDto passenger) {
        Optional<PassengerIdDto> passengerUpdated = passengerServices.update(id, passenger);
        return passengerUpdated
                .map(ResponseEntity::ok)
                .orElseGet(()->createNewPassenger(passenger));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PassengerIdDto> deletePassenger(@PathVariable("id") int id) {
        passengerServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<PassengerIdDto> createNewPassenger(PassengerDto passenger) {
        PassengerIdDto passengerIdDto = passengerServices.save(passenger);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(passengerIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(passengerIdDto);
    }
}
