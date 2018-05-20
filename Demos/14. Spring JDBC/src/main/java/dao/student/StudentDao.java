package dao.student;

import model.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(String name, int age, String neighbourhood, String nationality, String specialty) {
        String sql = "insert into students (name, age, neighbourhood, nationality, specialty) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, age, neighbourhood, nationality, specialty);
        System.out.println("Created Record Name = " + name +
                " Age = " + age +
                " Neighbourhood = " + neighbourhood +
                " Nationality = " + nationality +
                " Specialty = " + specialty);
    }

    public Student getStudent(int id) {
        String sql = "select * from students where id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, new StudentRowMapper());
    }

    public List<Student> getAllStudents() {
        String sql = "select * from students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    public void delete(int id) {
        String sql = "delete from students where id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    public void updateAge(int id, int age) {
        String sql = "update students set age = ? where id = ?";
        jdbcTemplate.update(sql, age, id);
        System.out.println("Updated Record with ID = " + id);
    }

    public void updateNationality(int id, String nationality) {
        String sql = "update students set nationality = ? where id = ?";
        jdbcTemplate.update(sql, nationality, id);
        System.out.println("Updated Record with ID = " + id);
    }
}