package imdb.persistence.user;

import imdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
class UserDao extends NamedParameterJdbcTemplate {

    @Autowired
    UserDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final String email, final String password) {
        final String sql = "INSERT INTO `users` (`email`, `password`) VALUES (?, ?)";
        return 1 == getJdbcOperations().update(sql, email, password);
    }

    User get(final String email) {
        final String sql = "SELECT * FROM `users` WHERE `email` = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
