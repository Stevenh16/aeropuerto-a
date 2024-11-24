package model.controller;

import lombok.AllArgsConstructor;
import model.dto.ClientDto;
import model.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;


    @GetMapping()
    public ResponseEntity<List<ClientDto>> getClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return clientService.findById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto client) {
        return createNewClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto client) {
        Optional<ClientDto> clientUpdated = clientService.update(id, client);
        return clientUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ClientDto> createNewClient(ClientDto client) {
        ClientDto clientIdDto = clientService.save(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(clientIdDto);
    }
}
