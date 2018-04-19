package config;

import dao.employee.EmployeeDao;
import dao.student.StudentDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDao();
    }

    @Bean
    public StudentDao studentDao() {
        return new StudentDao();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/pragmatic?createDatabaseIfNotExist=true");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}
