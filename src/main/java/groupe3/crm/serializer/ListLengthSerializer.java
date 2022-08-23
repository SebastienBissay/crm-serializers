package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.ListLength;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class ListLengthSerializer extends StdSerializer<ListLength>{
    
    public ListLengthSerializer() {
        this(null);
    }

    public ListLengthSerializer(Class<ListLength> listLength) {
        super(listLength);
    }

    @Override
    public void serialize(ListLength listLength, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("size", listLength.getLength());
        jsonGenerator.writeEndObject();
    }
}