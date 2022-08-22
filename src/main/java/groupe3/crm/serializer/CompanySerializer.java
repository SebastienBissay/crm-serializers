package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Company;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class CompanySerializer extends StdSerializer<Company> {

    public CompanySerializer() {
        this(null);
    }

    public CompanySerializer(Class<Company> company) {
        super(company);
    }

    @Override
    public void serialize(Company company, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", company.getId());
        jsonGenerator.writeStringField("name", company.getName());
        jsonGenerator.writeObjectFieldStart("client");
        {
            jsonGenerator.writeNumberField("id", company.getClient().getId());
            jsonGenerator.writeStringField("firstName", company.getClient().getFirstName());
            jsonGenerator.writeStringField("lastName", company.getClient().getLastName());
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField("phone", company.getPhone());
        jsonGenerator.writeStringField("email", company.getEmail());
        jsonGenerator.writeStringField("domain", company.getDomain());
        jsonGenerator.writeStringField("type", company.getType());
        jsonGenerator.writeEndObject();
    }
}
