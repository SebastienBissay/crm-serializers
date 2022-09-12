package groupe3.crm.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastien Bissay
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public JwtUserDetailsService(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        UserDetails userDetails = this.userServiceImplementation.loadUserByUsername(username);
        return new User(username, userDetails.getPassword(), new ArrayList<>());
    }
}
