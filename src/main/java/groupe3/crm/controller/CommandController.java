package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Command;
import groupe3.crm.model.ListLength;
import groupe3.crm.model.Product;
import groupe3.crm.service.ICommandService;
import groupe3.crm.service.IProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@Transactional
@RequestMapping("/commands")
public class CommandController {

    private final ICommandService commandService;
    private final IProductService productService;

    @Autowired
    public CommandController(ICommandService commandService, IProductService productService) {
        this.commandService = commandService;
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all commands", nickname = "Get all commands", response = Command.class)
    public ResponseEntity getAllCommands() {
        List<Command> commands = this.commandService.getAll();
        try {
            ListLength l = new ListLength(commands.size());
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(l) + new ObjectMapper().writeValueAsString(commands));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @ApiOperation(value = "Returns the command with given id", nickname = "Get a command", response = Command.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Command> command;
        try {
            command = this.commandService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (command.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(command.get()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new command", nickname = "Create a command")
    public ResponseEntity create(@RequestBody Command command) {
        try {
            this.commandService.create(command);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Command successfully created.");
    }

    @PutMapping(value = "/{id}/add_product", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Adds a product to the command with given id", nickname = "Add product to command")
    public ResponseEntity addProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        Optional<Command> command;
        try {
            command = this.commandService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (command.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found.");
        }
        try {
            Optional<Product> p = this.productService.getById(product.getId());
            if (p.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }
            this.commandService.addProduct(id, p.get());
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Command successfully modified: product added.");
    }

    @DeleteMapping(value = "/{id}/remove_product", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Removes a product to the command with given id", nickname = "Remove product to command")
    public ResponseEntity removeProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        Optional<Command> command;
        try {
            command = this.commandService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (command.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found.");
        }
        try {
            Optional<Product> p = this.productService.getById(product.getId());
            if (p.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }
            this.commandService.removeProduct(id, p.get());
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Command successfully modified: product removed.");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the command with given id", nickname = "Delete command")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Command> command;
        try {
            command = this.commandService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (command.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found.");
        }
        this.commandService.delete(id);
        return ResponseEntity.ok("Command successfully deleted.");
    }
    
    @GetMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of commands whose label contains 'label'", nickname = "Get commands by label", response = Command.class)
    public ResponseEntity getCommandsByLabel(@RequestBody Command command) {
        List<Command> commands = this.commandService.getByLabel(command.getLabel());
        try {
            ListLength l = new ListLength(commands.size());
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(l) + new ObjectMapper().writeValueAsString(commands));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
}
