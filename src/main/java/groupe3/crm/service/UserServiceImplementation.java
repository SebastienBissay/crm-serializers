package groupe3.crm.service;

import groupe3.crm.model.User;
import groupe3.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        super();
        this.tEntityClass = User.class.getSimpleName();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return user;
    }
    
    @Override
    public void create(User user) {
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        this.userRepository.save(user);
    }
    
    @Override
    public void update(Long id, User user) {
        if (user.getPassword() != null) {
            user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        }
        this.userRepository.save(user);
    }
}
