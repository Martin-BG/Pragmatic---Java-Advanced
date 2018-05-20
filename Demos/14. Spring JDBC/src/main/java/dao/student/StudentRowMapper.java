package dao.student;

import model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setNeighbourhood(rs.getString("neighbourhood"));
        student.setNationality(rs.getString("nationality"));
        student.setSpecialty(rs.getString("specialty"));
        return student;
    }
}
