package groupe3.crm.repository;

import groupe3.crm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastien Bissay
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
}
