package imdb.persistence.user_movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
class UserMoviesDao extends NamedParameterJdbcTemplate {

    @Autowired
    UserMoviesDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final String movieTitle, final String userEmail) {
        final String sql = "INSERT INTO `users_movies` (`movie_id`, `user_id`) " +
                "VALUES ((SELECT m.id FROM `movies` AS m WHERE m.title = ?), " +
                "(SELECT u.id FROM `users` AS u WHERE u.email = ?))";
        try {
            return 1 == getJdbcOperations().update(sql, movieTitle, userEmail);
        } catch (DataAccessException e) {
            return false;
        }
    }

    String getOwner(final String title) {
        final String sql = "SELECT u.email FROM `users_movies` AS um " +
                "LEFT JOIN `movies` AS m ON um.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON um.user_id = u.id " +
                "WHERE m.title = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{title}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    List<String> getAllForUser(final String email) {
        final String sql = "SELECT m.title FROM `users_movies` AS um " +
                "LEFT JOIN `movies` AS m ON um.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON um.user_id = u.id " +
                "WHERE u.email = ?";
        return getJdbcOperations().queryForList(sql, new Object[]{email}, String.class);
    }
}
