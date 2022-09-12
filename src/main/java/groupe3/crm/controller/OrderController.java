package groupe3.crm.controller;

import groupe3.crm.model.Order;
import groupe3.crm.repository.OrderRepository;
import groupe3.crm.service.OrderServiceImplementation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController extends AbstractController<Order, OrderRepository, OrderServiceImplementation>{
    
    
    @Autowired
    public OrderController(OrderServiceImplementation orderService) {
        super(orderService);
        this.tEntityClass = Order.class.getSimpleName();
    }
    
        @ApiOperation(value = "Returns the list of all orders", nickname = "Get all orders", response = Order.class)
    @Override
    public ResponseEntity getAll() {
        return super.getAll();
    }

    @ApiOperation(value = "Returns the order with given id", nickname = "Get an order", response = Order.class)
    @Override
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @ApiOperation(value = "Creates a new order", nickname = "Create an order")
    @Override
    public ResponseEntity create(@RequestBody Order order) {
        return super.create(order);
    }

    @ApiOperation(value = "Updates the order with given id", nickname = "Update order", response = Order.class)
    @Override
    public ResponseEntity update(@RequestBody Order order, @PathVariable("id") Long id) {
        return super.update(order, id);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the order with given id", nickname = "Delete order")
    @Override
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}
