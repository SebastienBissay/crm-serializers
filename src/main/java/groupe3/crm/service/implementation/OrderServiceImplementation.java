package groupe3.crm.service.implementation;

import groupe3.crm.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import groupe3.crm.repository.OrderRepository;
import groupe3.crm.service.IOrderService;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class OrderServiceImplementation implements IOrderService{
    
    private final OrderRepository orderRepository;
    
    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> getById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void update(Order order, Long id) {
        Order oldOrder = this.orderRepository.findById(id).orElse(null);
        if (oldOrder == null) {
            throw new NotFoundException();
        }
        oldOrder.copy(order);
        this.orderRepository.save(oldOrder);
    }

    @Override
    public void delete(Long id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public void create(Order order) {
        this.orderRepository.save(order);
    }
    
}
