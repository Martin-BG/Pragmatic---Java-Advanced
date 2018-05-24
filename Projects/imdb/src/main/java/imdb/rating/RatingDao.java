package imdb.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Repository
class RatingDao extends NamedParameterJdbcTemplate {

    @Autowired
    RatingDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final Rating rating) {
        final String sql = "INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) " +
                "VALUES ((SELECT m.id FROM `movies` AS m WHERE m.title = ?), " +
                "(SELECT u.id FROM `users` AS u WHERE u.email = ?), ?)";
        try {
            return 1 == getJdbcOperations()
                    .update(sql, rating.getMovieTitle(), rating.getUserEmail(), rating.getRating());
        } catch (DataAccessException e) {
            return false;
        }
    }

    boolean update(final Rating rating) {
        final String sql = "UPDATE `movies_user_ratings` AS mur SET mur.rating = ? WHERE " +
                "`movie_id` = (SELECT m.id FROM `movies` AS m WHERE m.title = ?) AND " +
                "`user_id` = (SELECT u.id FROM `users` AS u WHERE u.email = ?)";
        try {
            return 1 == getJdbcOperations()
                    .update(sql, rating.getRating(), rating.getMovieTitle(), rating.getUserEmail());
        } catch (DataAccessException e) {
            return false;
        }
    }

    boolean delete(final Rating rating) {
        final String sql = "DELETE FROM `movies_user_ratings` WHERE " +
                "`movie_id` = (SELECT m.id FROM `movies` AS m WHERE m.title = ?) AND " +
                "`user_id` = (SELECT u.id FROM `users` AS u WHERE u.email = ?);";
        try {
            return 1 == getJdbcOperations()
                    .update(sql, rating.getMovieTitle(), rating.getUserEmail());
        } catch (DataAccessException e) {
            return false;
        }
    }

    Rating get(final String email, final String title) {
        final String sql = "SELECT u.email, m.title, mur.rating FROM `movies_user_ratings` AS mur " +
                "LEFT JOIN `movies` AS m ON mur.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON mur.user_id = u.id " +
                "WHERE u.email = ? AND m.title = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{email, title}, new RatingRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    Set<Rating> getAllForUser(final String email) {
        final String sql = "SELECT u.email, m.title, mur.rating FROM `movies_user_ratings` AS mur " +
                "LEFT JOIN `movies` AS m ON mur.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON mur.user_id = u.id " +
                "WHERE u.email = ?";
        return new HashSet<>(getJdbcOperations().query(sql, new Object[]{email}, new RatingRowMapper()));
    }

    Set<Rating> getAllForMovie(final String title) {
        final String sql = "SELECT u.email, m.title, mur.rating FROM `movies_user_ratings` AS mur " +
                "LEFT JOIN `movies` AS m ON mur.movie_id = m.id " +
                "LEFT JOIN `users` AS u ON mur.user_id = u.id " +
                "WHERE m.title = ?";
        return new HashSet<>(getJdbcOperations().query(sql, new Object[]{title}, new RatingRowMapper()));
    }
}
