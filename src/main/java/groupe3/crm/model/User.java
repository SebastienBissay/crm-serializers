package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sebastien Bissay
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@JsonSerialize
public class User extends AbstractEntity<User> implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    @Email
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public void copy(User userData) {
        if (userData.email != null) {
            this.email = userData.email;
        }
        if (userData.password != null) {
            this.password = userData.password;
        }
        if (!userData.roles.isEmpty()) {
            this.roles = userData.roles;
        }
    }
    
    
}
