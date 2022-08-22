package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Order;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        this(null);
    }

    public OrderSerializer(Class<Order> client) {
        super(client);
    }

    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", order.getId());
        jsonGenerator.writeStringField("type", order.getType());
        jsonGenerator.writeStringField("label", order.getLabel());
        jsonGenerator.writeObjectFieldStart("client");
        {
            jsonGenerator.writeNumberField("id", order.getClient().getId());
            jsonGenerator.writeStringField("firstName", order.getClient().getFirstName());
            jsonGenerator.writeStringField("lastName", order.getClient().getLastName());
            jsonGenerator.writeStringField("company", order.getClient().getCompany());
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeNumberField("numberOfDays", order.getNumberOfDays());
        jsonGenerator.writeNumberField("unitPrice", order.getUnitPrice());
        jsonGenerator.writeNumberField("totalWithoutTax", order.getTotalWithoutTax());
        jsonGenerator.writeNumberField("totalWithTax", order.getTotalWithTax());
        jsonGenerator.writeStringField("status", order.getStatus());
        jsonGenerator.writeEndObject();
    }
}
