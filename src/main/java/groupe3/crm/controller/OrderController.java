package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Client;
import groupe3.crm.model.Order;
import groupe3.crm.service.IClientService;
import groupe3.crm.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/orders")
public class OrderController {
    
    private final IOrderService orderService;
    private final IClientService clientService;
    
    @Autowired
    public OrderController(IOrderService orderService, IClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }
    
        @GetMapping(produces = MediaType.APPLICATION_JSON)
        @ApiOperation(value = "Returns the list of all orders", nickname = "Get all orders", response = Order.class)
    public ResponseEntity getAllOrders() {
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(this.orderService.getAll()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the order with given id", nickname = "Get an order", response = Client.class)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Order> order;
        try {
            order = this.orderService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(order.get()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new order", nickname = "Create an order")
    public ResponseEntity create(@RequestBody Order order) {
        try {
            Optional<Client> client = this.clientService.getById(order.getClient().getId());
            if (client.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
            }
            order.setClient(client.get());
            this.orderService.create(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Order successfully created.");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates the order with given id", nickname = "Update order")
    public ResponseEntity update(@RequestBody Order order, @PathVariable("id") Long id) {
        try {
            this.orderService.update(order, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Order successfully modified.");
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the order with given id", nickname = "Delete order")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Order> order;
        try {
            order = this.orderService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        this.orderService.delete(id);
        return ResponseEntity.ok("Order successfully deleted.");
    }
}
