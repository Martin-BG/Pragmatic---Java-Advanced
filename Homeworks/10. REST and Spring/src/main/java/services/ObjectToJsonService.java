package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectToJsonService {

    private final ObjectMapper mapper;

    @Autowired
    public ObjectToJsonService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}