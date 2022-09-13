package groupe3.crm.repository;

import groupe3.crm.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastien Bissay
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     *
     */  
    public Optional<User> findByEmail(String email);
}
