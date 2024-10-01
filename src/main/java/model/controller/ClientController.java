package model.controller;

import model.dto.ClientDto;
import model.dto.ClientIdDto;
import model.service.ClientServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientServices clientService;

    public ClientController(ClientServices clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientIdDto>> getClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientIdDto> getClientById(@PathVariable int id) {
        return clientService.findById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ClientIdDto> createClient(@RequestBody ClientDto client) {
        return createNewClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientIdDto> updateClient(@PathVariable int id, @RequestBody ClientDto client) {
        Optional<ClientIdDto> clientUpdated = clientService.update(id, client);
        return clientUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ClientIdDto> createNewClient(ClientDto client) {
        ClientIdDto clientIdDto = clientService.save(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(clientIdDto);
    }
}
