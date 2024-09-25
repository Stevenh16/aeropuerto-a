package model.controller;

import model.entity.Client;
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
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        return clientService.findClientById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return createNewClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client client) {
        Optional<Client> clientUpdated = clientService.updateClient(id, client);
        return clientUpdated
                .map(ResponseEntity::ok)
                .orElseGet(() -> createNewClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Client> createNewClient(Client c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.getId())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
