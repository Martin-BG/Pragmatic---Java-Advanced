package services.jsonconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonConverter {

    private final ObjectMapper mapper;

    @Autowired
    public JsonConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void printObjectToJson(Object object) throws JsonProcessingException {
        String convertedObject = mapper.writeValueAsString(object);
        System.out.println(convertedObject);
    }

}
