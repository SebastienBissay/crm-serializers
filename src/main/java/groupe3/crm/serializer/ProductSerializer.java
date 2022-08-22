package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Product;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class ProductSerializer extends StdSerializer<Product> {

    public ProductSerializer() {
        this(null);
    }

    public ProductSerializer(Class<Product> product) {
        super(product);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeStringField("description", product.getDescription());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeEndObject();
    }
}
