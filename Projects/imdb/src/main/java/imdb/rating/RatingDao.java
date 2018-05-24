package imdb.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
class RatingDao extends NamedParameterJdbcTemplate {

    @Autowired
    RatingDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final String movieTitle, final String userEmail, final int rating) {
        final String sql = "INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) " +
                "VALUES ((SELECT m.id FROM `movies` AS m WHERE m.title = ?), " +
                "(SELECT u.id FROM `users` AS u WHERE u.email = ?), ?)";
        try {
            return 1 == getJdbcOperations().update(sql, movieTitle, userEmail, rating);
        } catch (DataAccessException e) {
            return false;
        }
    }

    boolean update(final String movieTitle, final String userEmail, final int rating) {
        final String sql = "UPDATE `movies_user_ratings` AS mur SET mur.rating = ? WHERE " +
                "`movie_id` = (SELECT m.id FROM `movies` AS m WHERE m.title = ?) AND " +
                "`user_id` = (SELECT u.id FROM `users` AS u WHERE u.email = ?)";
        try {
            return 1 == getJdbcOperations().update(sql, rating, movieTitle, userEmail);
        } catch (DataAccessException e) {
            return false;
        }
    }

    boolean delete(final String movieTitle, final String userEmail) {
        final String sql = "DELETE FROM `movies_user_ratings` WHERE " +
                "`movie_id` = (SELECT m.id FROM `movies` AS m WHERE m.title = ?) AND " +
                "`user_id` = (SELECT u.id FROM `users` AS u WHERE u.email = ?);";
        try {
            return 1 == getJdbcOperations().update(sql, movieTitle, userEmail);
        } catch (DataAccessException e) {
            return false;
        }
    }

    Integer get(final String title, final String email) {
        final String sql = "SELECT mur.rating FROM `movies_user_ratings` AS mur " +
                "LEFT JOIN `movies` AS m ON mur.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON mur.user_id = u.id " +
                "WHERE u.email = ? AND m.title = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{email, title}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    List<String> getAllForUser(final String email) {
        final String sql = "SELECT m.title FROM `movies_user_ratings` AS mur " +
                "LEFT JOIN `movies` AS m ON mur.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON mur.user_id = u.id " +
                "WHERE u.email = ?";
        return getJdbcOperations().queryForList(sql, new Object[]{email}, String.class);
    }

    List<Integer> getAllForMovie(final String title) {
        final String sql = "SELECT mur.rating FROM `movies_user_ratings` AS mur " +
                "LEFT JOIN `movies` AS m ON mur.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON mur.user_id = u.id " +
                "WHERE m.title = ?";
        return getJdbcOperations().queryForList(sql, new Object[]{title}, Integer.class);
    }
}
