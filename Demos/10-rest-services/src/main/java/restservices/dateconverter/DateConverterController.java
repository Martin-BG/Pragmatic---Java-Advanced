package restservices.dateconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dateconverter")
public class DateConverterController {

    private final DateConverterService service;

    @Autowired
    public DateConverterController(DateConverterService service) {
        this.service = service;
    }

    @RequestMapping("")
    public String convert(@RequestParam(value = "date") String date) {
        return service.convert(date);
    }

}
