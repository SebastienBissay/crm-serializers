package groupe3.crm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import groupe3.crm.model.Role;
import groupe3.crm.model.User;
import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }
    
    public UserSerializer(Class<User> user) {
        super(user);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeArrayFieldStart("roles");
        for (Role role : user.getRoles()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("title", role.getTitle());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

}
