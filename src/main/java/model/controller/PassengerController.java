package model.controller;

import lombok.AllArgsConstructor;
import model.dto.PassengerDto;
import model.service.PassengerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/passengers")
@AllArgsConstructor
public class PassengerController {
    private final PassengerServices passengerServices;
    @GetMapping()
    public ResponseEntity<List<PassengerDto>> getPassengers() {
        return ResponseEntity.ok(passengerServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable("id") int id) {
        return passengerServices.getById(id)
                .map(p->ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passenger) {
        return createNewPassenger(passenger);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable("id") int id,@RequestBody PassengerDto passenger) {
        Optional<PassengerDto> passengerUpdated = passengerServices.update(id, passenger);
        return passengerUpdated
                .map(ResponseEntity::ok)
                .orElseGet(()->createNewPassenger(passenger));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PassengerDto> deletePassenger(@PathVariable("id") int id) {
        passengerServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<PassengerDto> createNewPassenger(PassengerDto passenger) {
        PassengerDto passengerIdDto = passengerServices.save(passenger);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(passengerIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(passengerIdDto);
    }
}
