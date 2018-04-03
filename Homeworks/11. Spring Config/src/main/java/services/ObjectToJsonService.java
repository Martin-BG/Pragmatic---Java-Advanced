package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonService {

    private final ObjectMapper mapper;

    public ObjectToJsonService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}