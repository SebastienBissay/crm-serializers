package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.CompanySerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "companies")
@Data
@NoArgsConstructor
@JsonSerialize(using = CompanySerializer.class)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Length(min = 10, max = 17)
    private String phone;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(name = "dom")
    // "domain" is a reserved SQL keyword
    private String domain;

    private String type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Client client;

    public void copy(Company companyData) {
        if (companyData.client != null) {
            this.client = companyData.client;
        }
        if (companyData.domain != null) {
            this.domain = companyData.domain;
        }
        if (companyData.email != null) {
            this.email = companyData.email;
        }
        if (companyData.name != null) {
            this.name = companyData.name;
        }
        if (companyData.phone != null) {
            this.phone = companyData.phone;
        }
        if (companyData.type != null) {
            this.type = companyData.type;
        }
    }
}
