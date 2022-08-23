package groupe3.crm.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import groupe3.crm.serializer.ListLengthSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sebastien Bissay
 */
@Data
@NoArgsConstructor
@JsonSerialize(using = ListLengthSerializer.class)
public class ListLength {
    private Integer length;
    
    public ListLength(Integer length) {
        this.length = length;
    }
}
