package groupe3.crm.repository;

import groupe3.crm.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastien Bissay
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
