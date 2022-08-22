package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Client;
import groupe3.crm.model.Order;
import groupe3.crm.service.IClientService;
import groupe3.crm.service.IOrderService;
import java.util.List;
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
    public ResponseEntity getAllOrders() {
        List<Order> orders = this.orderService.getAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(orders));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
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

    @PostMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity create(@RequestBody Order order, @PathVariable("id") Long clientId) {
        try {
            Optional<Client> client = this.clientService.getById(clientId);
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
    public ResponseEntity update(@RequestBody Order order, @PathVariable("id") Long id) {
        try {
            this.orderService.update(order, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Order successfully modified.");
    }
    
    @DeleteMapping("/{id}")
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
