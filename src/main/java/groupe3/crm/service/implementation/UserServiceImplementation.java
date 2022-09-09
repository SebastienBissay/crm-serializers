package groupe3.crm.service.implementation;

import groupe3.crm.model.User;
import groupe3.crm.repository.UserRepository;

/**
 *
 * @author Sebastien Bissay
 */
public class UserServiceImplementation extends AbstractServiceImplementation<User, UserRepository> {

    public UserServiceImplementation() {
        super();
        this.tEntityClass = User.class.getSimpleName();
    }
}
