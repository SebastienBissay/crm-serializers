package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.OperationSerializer;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Sebastien Bissay
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "operations")
@JsonSerialize(using = OperationSerializer.class)
public class Operation implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, name = "usr")
    // "user" is a reserved SQL keyword
    private String user;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, name = "operation_time")
    // "time" is a reserved SQL keyword
    private Date time;
    
    @Column(columnDefinition = "ENUM('CREATE', 'DELETE', 'MODIFY')", nullable = false)
    private String type;
    
    @Column(nullable = false)
    private String target;
    
    @Column(nullable = false)
    private Long targetId;

    public Operation(String user, String type, String target, Long targetId) {
        this.user = user;
        this.type = type;
        this.target = target;
        this.targetId = targetId;
    }
}
