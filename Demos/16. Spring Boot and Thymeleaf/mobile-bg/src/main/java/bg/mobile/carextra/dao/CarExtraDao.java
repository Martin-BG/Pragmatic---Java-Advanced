package bg.mobile.carextra.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CarExtraDao extends NamedParameterJdbcTemplate {

    @Autowired
    public CarExtraDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public void add(long carId, String extra) {
        String sql = "INSERT INTO car_extra (car_id, extra_id) " +
                "VALUES (?, (SELECT id FROM extra e WHERE e.name = ?))";
        getJdbcOperations().update(sql, carId, extra);
    }

    public List<String> getAvailableExtras() {
        String sql = "SELECT name FROM extra";
        return getJdbcOperations().queryForList(sql, String.class);
    }

    public Set<String> getExtrasForCar(long carId) {
        String sql = "SELECT name FROM extra e " +
                "INNER JOIN car_extra ce ON e.id = ce.extra_id " +
                "WHERE ce.car_id = " + carId;
        return new HashSet<>(getJdbcOperations().queryForList(sql, String.class));
    }

    public void deleteExtrasForCar(long carId) {
        String sql = "DELETE FROM car_extra WHERE car_id = ?";
        getJdbcOperations().update(sql, carId);
    }
}
