package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Command;
import groupe3.crm.model.Product;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class CommandSerializer extends StdSerializer<Command> {

    public CommandSerializer() {
        this(null);
    }

    public CommandSerializer(Class<Command> command) {
        super(command);
    }

    @Override
    public void serialize(Command command, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", command.getId());
        jsonGenerator.writeStringField("label", command.getLabel());
        jsonGenerator.writeArrayFieldStart("products");
            for (Product product : command.getProducts()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", product.getId());
                jsonGenerator.writeStringField("name", product.getName());
                jsonGenerator.writeNumberField("price", product.getPrice());
                jsonGenerator.writeEndObject();
            }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeNumberField("totalPrice", command.getTotalPrice());
        jsonGenerator.writeEndObject();
    }
}
