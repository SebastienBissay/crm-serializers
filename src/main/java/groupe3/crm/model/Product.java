package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.ProductSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sebastien Bissay
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@JsonSerialize(using = ProductSerializer.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @PositiveOrZero
    @Column(nullable = false)
    private Double price;

    public void copy(Product productData) {
        if (productData.description != null) {
            this.description = productData.description;
        }
        if (productData.name != null) {
            this.name = productData.name;
        }
        if (productData.price != null) {
            this.price = productData.price;
        }
    }
}
