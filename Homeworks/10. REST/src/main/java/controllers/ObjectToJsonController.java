package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ObjectToJsonService;

@RestController
@RequestMapping("/objecttojson")
public class ObjectToJsonController {

    private final ObjectToJsonService objectToJsonService;

    @Autowired
    public ObjectToJsonController(ObjectToJsonService objectToJsonService) {
        this.objectToJsonService = objectToJsonService;
    }

    @RequestMapping("")
    public String convertObjectToJson() throws JsonProcessingException {

        Person person = new Person("Pesho Peshev", 27, true, "Sofia");

        return this.objectToJsonService.convertObjectToJson(person);
    }
}
