package model.controller;

import model.entity.Reserve;
import model.service.ReserveServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reserves")
public class ReserveController {
    private final ReserveServices reserveServices;
    public ReserveController(ReserveServices reserveServices) {
        this.reserveServices = reserveServices;
    }
    @GetMapping
    public ResponseEntity<List<Reserve>> getAllReserves() {
        return ResponseEntity.ok(reserveServices.findAllReserves());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reserve> getReserveById(@PathVariable("id") int id) {
        return reserveServices.findReserveById(id)
                .map(r->ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Reserve> createReserve(@RequestBody Reserve reserve) {
        return createNewReserve(reserve);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reserve> updateReserve(@PathVariable int id, @RequestBody Reserve reserve) {
        Optional<Reserve> reserveUpdate = reserveServices.updateReserve(id,reserve);
        return reserveUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()->createNewReserve(reserve));
    }
    private ResponseEntity<Reserve> createNewReserve(Reserve reserve) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reserve.getId())
                .toUri();
        return ResponseEntity.created(location).body(reserve);
    }
}
