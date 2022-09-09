package groupe3.crm.service.implementation;

import groupe3.crm.model.User;
import groupe3.crm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class UserServiceImplementation extends AbstractServiceImplementation<User, UserRepository> {

    public UserServiceImplementation() {
        super();
        this.tEntityClass = User.class.getSimpleName();
    }
}
