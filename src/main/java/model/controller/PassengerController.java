package model.controller;

import model.entity.Passenger;
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
    public ResponseEntity<List<Passenger>> getPassengers() {
        return ResponseEntity.ok(passengerServices.getAllPassengers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable("id") int id) {
        return passengerServices.getPassengerById(id)
                .map(p->ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        return createNewPassenger(passenger);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable("id") int id,@RequestBody Passenger passenger) {
        Optional<Passenger> passengerUpdated = passengerServices.updatePassenger(id, passenger);
        return passengerUpdated
                .map(ResponseEntity::ok)
                .orElseGet(()->createNewPassenger(passenger));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> deletePassenger(@PathVariable("id") int id) {
        passengerServices.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<Passenger> createNewPassenger(Passenger passenger) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(passenger.getId())
                .toUri();
        return ResponseEntity.created(location).body(passenger);
    }
}
