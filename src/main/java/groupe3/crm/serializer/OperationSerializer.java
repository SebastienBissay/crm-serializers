package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Operation;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Sebastien Bissay
 */
public class OperationSerializer extends StdSerializer<Operation> {

    public OperationSerializer() {
        this(null);
    }

    public OperationSerializer(Class<Operation> operation) {
        super(operation);
    }

    @Override
    public void serialize(Operation operation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy, hh:mm");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", operation.getId());
        jsonGenerator.writeStringField("date", formatter.format(operation.getTime()));
        jsonGenerator.writeStringField("user", operation.getUser());
        jsonGenerator.writeStringField("type", operation.getType());
        jsonGenerator.writeStringField("target", operation.getTarget());
        jsonGenerator.writeNumberField("target_id", operation.getTargetId());
        jsonGenerator.writeEndObject();
    }
}