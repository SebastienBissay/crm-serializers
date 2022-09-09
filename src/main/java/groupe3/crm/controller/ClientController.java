package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Client;
import groupe3.crm.repository.ClientRepository;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import groupe3.crm.service.implementation.ClientServiceImplementation;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
public class ClientController extends AbstractController<Client, ClientRepository, ClientServiceImplementation> {

    private final ClientServiceImplementation clientService;

    @Autowired
    public ClientController(ClientServiceImplementation clientService) {
        super(clientService);
        this.clientService = clientService;
        this.tEntityClass = Client.class.getSimpleName();
    }

    @Override
    @ApiOperation(value = "Returns the list of all clients", nickname = "Get all clients", response = Client.class)
    public ResponseEntity getAll() {
        return super.getAll();
    }

    @ApiOperation(value = "Returns the client with given id", nickname = "Get a client", response = Client.class)
    @Override
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @ApiOperation(value = "Creates a new client", nickname = "Create a client")
    @Override
    public ResponseEntity create(@RequestBody Client client) {
        return super.create(client);
    }

    @ApiOperation(value = "Updates the client with given id", nickname = "Update client", response = Client.class)
    @Override
    public ResponseEntity update(@RequestBody Client client, @PathVariable("id") Long id) {
        return super.update(client, id);
    }

    @ApiOperation(value = "Deletes the client with given id", nickname = "Delete client")
    @Override
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
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
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
}
