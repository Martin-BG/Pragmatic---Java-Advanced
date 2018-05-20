package restservices.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translate")
public class TranslatorController {

    private final TranslatorService service;

    @Autowired
    public TranslatorController(TranslatorService service) {
        this.service = service;
    }

    @RequestMapping("")
    public String translate(@RequestParam(value = "from") String from,
                            @RequestParam(value = "to") String to,
                            @RequestParam(value = "word") String word) throws Exception {
        return service.translate(from, to, word);
    }

}
