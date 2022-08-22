package groupe3.crm.repository;

import groupe3.crm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sebastien Bissay
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
}
