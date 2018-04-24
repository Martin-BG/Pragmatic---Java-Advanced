package library.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.config.Messages;
import library.exceptions.JsonParsingException;
import library.parser.api.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonParserImpl implements JsonParser {

    private final ObjectMapper mapper;
    private final Messages messages;

    @Autowired
    public JsonParserImpl(final ObjectMapper mapper,
                          final Messages messages) {
        this.mapper = mapper;
        this.messages = messages;
    }

    @Override
    public <T> String write(final T object) throws JsonParsingException {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonParsingException(this.messages.get("error.object.to.json") + e.getMessage(), e);
        }
    }

    @Override
    public <T> T read(final Class<T> objectClass, final String json) throws JsonParsingException {
        try {
            return this.mapper.readValue(json, objectClass);
        } catch (Exception e) {
            throw new JsonParsingException(this.messages.get("error.json.to.object") + e.getMessage(), e);
        }
    }
}
