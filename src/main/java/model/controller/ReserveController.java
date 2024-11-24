package model.controller;

import lombok.AllArgsConstructor;
import model.dto.ReserveDto;
import model.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reserves")
@AllArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;

    @GetMapping
    public ResponseEntity<List<ReserveDto>> getReserves() {
        return ResponseEntity.ok(reserveService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReserveDto> getReserveById(@PathVariable("id") Long id) {
        return reserveService.findById(id)
                .map(r->ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<ReserveDto> createReserve(@RequestBody ReserveDto reserve) {
        return createNewReserve(reserve);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReserveDto> updateReserve(@PathVariable Long id, @RequestBody ReserveDto reserve) {
        Optional<ReserveDto> reserveUpdate = reserveService.update(id,reserve);
        return reserveUpdate
                .map(ResponseEntity::ok)
                .orElseGet(()->createNewReserve(reserve));
    }
    private ResponseEntity<ReserveDto> createNewReserve(ReserveDto reserve) {
        ReserveDto reserveIdDto = reserveService.save(reserve);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reserveIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(reserveIdDto);
    }
}
