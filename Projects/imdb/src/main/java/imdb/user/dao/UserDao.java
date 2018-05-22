package imdb.user.dao;

import imdb.user.model.User;
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
        String sql = "INSERT INTO `users` (`email`, `password`) VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, user.getEmail(), user.getPassword());
        return success == 1;
    }


    public User getUser(final String email) {
        final String sql = "SELECT * FROM `users` WHERE `email` = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public long getIdByEmail(String email) {
        final String sql = "SELECT `id` FROM `users` WHERE `email` = ?";
        return getJdbcOperations().queryForObject(sql, new Object[]{email}, Long.class);
    }
}
