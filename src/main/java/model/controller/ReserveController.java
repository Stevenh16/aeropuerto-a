package model.controller;

import model.dto.ReserveDto;
import model.dto.ReserveIdDto;
import model.mapper.ReserveMapper;
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
    private final ReserveMapper reserveMapper;

    public ReserveController(ReserveServices reserveServices, ReserveMapper reserveMapper) {
        this.reserveServices = reserveServices;
        this.reserveMapper = reserveMapper;
    }
    @GetMapping
    public ResponseEntity<List<ReserveIdDto>> getReserves() {
        return ResponseEntity.ok(reserveServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReserveIdDto> getReserveById(@PathVariable("id") int id) {
        return reserveServices.findById(id)
                .map(r->ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<ReserveIdDto> createReserve(@RequestBody ReserveDto reserve) {
        return createNewReserve(reserve);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReserveIdDto> updateReserve(@PathVariable int id, @RequestBody ReserveDto reserve) {
        Optional<ReserveIdDto> reserveUpdate = reserveServices.update(id,reserve);
        return reserveUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()->createNewReserve(reserve));
    }
    private ResponseEntity<ReserveIdDto> createNewReserve(ReserveDto reserve) {
        ReserveIdDto reserveIdDto = reserveServices.save(reserve);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reserveIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(reserveIdDto);
    }
}
