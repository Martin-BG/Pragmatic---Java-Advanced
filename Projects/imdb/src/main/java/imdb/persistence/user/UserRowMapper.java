package imdb.persistence.user;

import imdb.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

final class UserRowMapper implements RowMapper<User> {

    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new User(
                rs.getString("email"),
                rs.getString("password"));
    }
}
