package bg.mobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public RedirectAttributes getRedirectAttributes() {
        return new RedirectAttributesModelMap();
    }

    @Bean
    public DataSource sqlDataSource() {
        DriverManagerDataSource sqlDataSource = new DriverManagerDataSource();
        sqlDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        sqlDataSource.setUrl("jdbc:mysql://localhost:3306/mobilebg_demo");
        sqlDataSource.setUsername("root");
        sqlDataSource.setPassword("root");
        return sqlDataSource;
    }
}
