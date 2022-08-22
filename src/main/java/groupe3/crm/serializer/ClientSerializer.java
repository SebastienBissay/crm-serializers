package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Client;
import groupe3.crm.model.Order;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class ClientSerializer extends StdSerializer<Client>{
    public ClientSerializer() {
        this(null);
    }
    
    public ClientSerializer(Class<Client> client) {
        super(client);
    }
    @Override
    public void serialize(Client client, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", client.getId());
        jsonGenerator.writeStringField("firstName", client.getFirstName());
        jsonGenerator.writeStringField("lastName", client.getLastName());
        jsonGenerator.writeStringField("company", client.getCompany());
        jsonGenerator.writeStringField("email", client.getEmail());
        jsonGenerator.writeStringField("phone", client.getPhone());
        jsonGenerator.writeStringField("address", client.getAddress());
        jsonGenerator.writeNumberField("zipCode", client.getZipCode());
        jsonGenerator.writeStringField("city", client.getCity());
        jsonGenerator.writeStringField("country", client.getCountry());
        jsonGenerator.writeBooleanField("active", client.getActive());
        
        jsonGenerator.writeArrayFieldStart("orders");
        for (Order o : client.getOrders()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", o.getId());
            jsonGenerator.writeStringField("type", o.getType());
            jsonGenerator.writeStringField("label", o.getLabel());
            jsonGenerator.writeStringField("status", o.getStatus());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        
        jsonGenerator.writeEndObject();
    }
}
