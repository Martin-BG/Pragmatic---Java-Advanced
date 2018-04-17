package dao;

import model.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String name, int age) {
        String SQL = "insert into student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQL, name, age);
        System.out.println("Created Record Name = " + name + " Age = " + age);
    }

    public Student getStudent(int id) {
        String SQL = "select * from student where id = ?";
        Student student = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StudentRowMapper());
        return student;
    }

    public List<Student> listStudents() {
        String SQL = "select * from student";
        List<Student> students = jdbcTemplateObject.query(SQL, new StudentRowMapper());
        return students;
    }

    public void delete(int id) {
        String SQL = "delete from student where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    public void update(int id, int age) {
        String SQL = "update student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id);
    }
}