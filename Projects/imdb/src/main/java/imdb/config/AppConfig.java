package imdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private final Environment env;

    @Autowired
    public AppConfig(final Environment env) {
        this.env = env;
    }

    @Bean
    public RedirectAttributes getRedirectAttributes() {
        return new RedirectAttributesModelMap();
    }

    @Bean
    public DataSource sqlDataSource() {
        DriverManagerDataSource sqlDataSource = new DriverManagerDataSource();
        sqlDataSource.setDriverClassName(this.env.getProperty("spring.datasource.driverClassName"));
        sqlDataSource.setUrl(this.env.getProperty("spring.datasource.url"));
        sqlDataSource.setUsername(this.env.getProperty("spring.datasource.username"));
        sqlDataSource.setPassword(this.env.getProperty("spring.datasource.password"));
        return sqlDataSource;
    }

    public String get(String code) {
        return this.env.getProperty(code);
    }
}
