package bg.mobile.user.dao;

import bg.mobile.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao extends NamedParameterJdbcTemplate {

    @Autowired
    public UserDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(User user) {
        String sql = "INSERT INTO user (email, password) " +
                "VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, user.getEmail(), user.getPassword());
        return success == 1;
    }

    public User getUser(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            return getJdbcOperations().queryForObject(sql,
                    new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public long getIdByEmail(String email) {
        String sql = "SELECT id FROM user WHERE email = ?";
        return getJdbcOperations().queryForObject(sql, new Object[]{email}, Long.class);
    }
}
