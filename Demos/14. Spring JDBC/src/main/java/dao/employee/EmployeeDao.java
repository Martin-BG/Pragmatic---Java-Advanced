package dao.employee;

import model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(String name, int age, String department, double salary) {
        String sql = "insert into employees (name, age, department, salary) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, age, department, salary);
        System.out.println("Created Record Name = " + name +
                " Age = " + age +
                " Department = " + department +
                " Salary = " + salary);
    }

    public void updateSalary(int id, double salary) {
        String sql = "update employees set salary = ? where id = ?";
        jdbcTemplate.update(sql, salary, id);
        System.out.println("Updated Record with ID = " + id);
    }

    public List<Employee> getAllEmployees() {
        String sql = "select * from employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public void delete(int id) {
        String sql = "delete from employees where id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    public Employee getEmployee(int id) {
        String sql = "select * from employees where id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, new EmployeeRowMapper());
    }
}
