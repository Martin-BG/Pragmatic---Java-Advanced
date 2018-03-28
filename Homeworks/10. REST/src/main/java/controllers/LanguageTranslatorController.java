package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.LanguageTranslatorService;

import java.io.IOException;

@RestController
@RequestMapping("/translator")
public class LanguageTranslatorController {

    private final LanguageTranslatorService languageTranslatorService;

    @Autowired
    public LanguageTranslatorController(LanguageTranslatorService languageTranslatorService) {
        this.languageTranslatorService = languageTranslatorService;
    }

    @RequestMapping("")
    public String translate(@RequestParam(value = "from") String from,
                            @RequestParam(value = "to") String to,
                            @RequestParam(value = "text") String text) throws IOException {
        return this.languageTranslatorService.translate(from, to, text);
    }

}
