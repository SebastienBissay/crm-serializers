package groupe3.crm.service;

import groupe3.crm.model.Order;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastien Bissay
 */
public interface IOrderService {
    
    public List<Order> getAll();
    
    public Optional<Order> getById(Long id);
    
    public void create(Order order);
    
    public void update(Order order, Long id);
    
    public void delete(Long id);
}
