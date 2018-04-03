package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.WordChecker;
import helpers.WordReader;
import helpers.WordWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.*;

@Configuration
public class AppConfig {

    @Bean
    public DateToDayConverterService dateToDayConverterService() {
        return new DateToDayConverterService();
    }

    @Bean
    public CalculatorService calculatorService() {
        return new CalculatorService();
    }

    @Bean
    public LanguageTranslatorService languageTranslatorService() {
        return new LanguageTranslatorService();
    }

    @Bean
    public ObjectToJsonService objectToJsonService(ObjectMapper objectMapper) {
        return new ObjectToJsonService(objectMapper);
    }

    @Bean
    public WordService wordService(WordChecker wordChecker, WordWriter wordWriter, WordReader wordReader) {
        return new WordService(wordChecker, wordWriter, wordReader);
    }

    @Bean
    public WordChecker wordChecker() {
        return new WordChecker();
    }

    @Bean
    public WordWriter wordWriter() {
        return new WordWriter();
    }

    @Bean
    public WordReader wordReader() {
        return new WordReader();
    }

}
