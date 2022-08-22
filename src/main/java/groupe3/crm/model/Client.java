package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.ClientSerializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Sebastien Bissay
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@JsonSerialize(using = ClientSerializer.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String company;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Length(min = 10, max = 17)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String city;

    private String country;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    public void copy(Client clientData) {
        if (clientData.address != null) {
            this.address = clientData.address;
        }
        if (clientData.active != null) {
            this.active = clientData.active;
        }
        if (clientData.city != null) {
            this.city = clientData.city;
        }
        if (clientData.company != null) {
            this.company = clientData.company;
        }
        if (clientData.country != null) {
            this.country = clientData.country;
        }
        if (clientData.email != null) {
            this.email = clientData.email;
        }
        if (clientData.firstName != null) {
            this.firstName = clientData.firstName;
        }
        if (clientData.lastName != null) {
            this.lastName = clientData.lastName;
        }
        if (!clientData.orders.isEmpty()) {
            this.orders.addAll(clientData.orders);
        }
        if (clientData.phone != null) {
            this.phone = clientData.phone;
        }
        
        if (clientData.zipCode != null) {
            this.zipCode = clientData.zipCode;
        }
    }
}
