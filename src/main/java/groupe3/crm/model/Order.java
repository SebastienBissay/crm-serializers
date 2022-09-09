package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.OrderSerializer;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sebastien Bissay
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@JsonSerialize(using = OrderSerializer.class)
public class Order extends AbstractEntity<Order> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    
    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private String label;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Client client;
    
    @Column(nullable = false)
    @Positive
    private Integer numberOfDays;
    
    @Column(nullable = false)
    @PositiveOrZero
    private Double unitPrice;
    
    @Column(columnDefinition = "ENUM('CANCELED', 'OPTION', 'CONFIRMED')")
    private String status;
    
    public Double getTotalWithoutTax() {
        return this.numberOfDays * this.unitPrice;
    }
    
    public Double getTotalWithTax() {
        return this.getTotalWithoutTax() * 1.20;
    }
    
    @Override
    public void copy(Order orderData) {
        if(orderData.client != null) {
            this.client = orderData.client;
        }
        if (orderData.label != null) {
            this.label = orderData.label;
        }
        if (orderData.numberOfDays != null) {
            this.numberOfDays = orderData.numberOfDays;
        }
        if (orderData.status != null) {
            this.status = orderData.status;
        }
        if (orderData.type != null) {
            this.type = orderData.type;
        }
        if (orderData.unitPrice != null) {
            this.unitPrice = orderData.unitPrice;
        }
    }
}
