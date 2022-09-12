package groupe3.crm.service;

import groupe3.crm.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import groupe3.crm.repository.OrderRepository;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class OrderServiceImplementation extends AbstractServiceImplementation<Order, OrderRepository> {

    public OrderServiceImplementation() {
        super();
        this.tEntityClass = Order.class.getSimpleName();
    }
}
