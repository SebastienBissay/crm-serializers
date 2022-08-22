package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.CommandSerializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sebastien Bissay
 */
@Entity
@Table(name = "commands")
@Data
@NoArgsConstructor
@JsonSerialize(using = CommandSerializer.class)
public class Command {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="command_products",
            joinColumns = @JoinColumn(name = "command_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
            )
    private List<Product> products = new ArrayList<>();
    
    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
    
    public void copy(Command commandData) {
        if (!commandData.products.isEmpty()) {
            this.products.clear();
            this.products.addAll(commandData.products);
        }
    }
    
    public Double getTotalPrice() {
        Double price = 0.D;
        for (Product product : this.products) {
            price += product.getPrice();
        }
        return price;
    }
}
