package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Client;
import groupe3.crm.model.ListLength;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import groupe3.crm.service.IClientService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all clients", nickname = "Get all clients", response = Client.class)
    public ResponseEntity getAllClients() {
        List<Client> clients = this.clientService.getAll();
        try {
            ListLength l = new ListLength(clients.size());
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(l) + new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @ApiOperation(value = "Returns the client with given id", nickname = "Get a client", response = Client.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Client> client;
        try {
            client = this.clientService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(client.get()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new client", nickname = "Create a client")
    public ResponseEntity create(@RequestBody Client client) {
        try {
            this.clientService.create(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created.");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates the client with given id", nickname = "Update client")
    public ResponseEntity update(@RequestBody Client client, @PathVariable("id") Long id) {
        try {
            this.clientService.update(client, id);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Client successfully modified.");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the client with given id", nickname = "Delete client")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Client> client;
        try {
            client = this.clientService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        this.clientService.delete(id);
        return ResponseEntity.ok("Client successfully deleted.");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of clients whose first name or last name contains 'name'", nickname = "Get clients by name", response = Client.class)
    public ResponseEntity getClientsByName(@RequestBody Client client) {
        List<Client> clients = new ArrayList<>();
        if (client.getFirstName() != null) {
            clients.addAll(this.clientService.getByName(client.getFirstName()));
        }
        if (client.getLastName() != null) {
            clients.addAll(this.clientService.getByName(client.getLastName()));
        }
        try {
            ListLength l = new ListLength(clients.size());
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(l) + new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
}
