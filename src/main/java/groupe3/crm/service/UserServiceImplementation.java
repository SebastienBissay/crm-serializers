package groupe3.crm.service;

import groupe3.crm.model.User;
import groupe3.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class UserServiceImplementation extends AbstractServiceImplementation<User, UserRepository> implements UserDetailsService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        super();
        this.tEntityClass = User.class.getSimpleName();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return user;
    }
}
