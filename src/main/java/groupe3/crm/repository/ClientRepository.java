package groupe3.crm.repository;

import groupe3.crm.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastien Bissay
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
    public List<Client> findByFirstNameContainingOrLastNameContainingAllIgnoringCase(String firstName, String lastName);
}
