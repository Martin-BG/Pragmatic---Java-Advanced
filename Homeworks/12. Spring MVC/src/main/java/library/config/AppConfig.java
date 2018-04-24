package library.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    @Bean
    public RedirectAttributes getRedirectAttributes() {
        return new RedirectAttributesModelMap();
    }
}
