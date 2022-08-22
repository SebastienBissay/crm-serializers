package groupe3.crm.repository;

import groupe3.crm.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastien Bissay
 */
public interface CommandRepository extends JpaRepository<Command, Long>{
}
