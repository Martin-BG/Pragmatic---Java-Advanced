package imdb.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
class RatingDao extends NamedParameterJdbcTemplate {

    @Autowired
    public RatingDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(final Rating rating, final String userEmail) {
        final String sql = "INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) " +
                "VALUES (?, (SELECT u.id FROM `users` AS u WHERE u.email='?'), ?)";
        int success = getJdbcOperations().update(sql, rating.getMovie().getId(), userEmail, rating.getRating());
        return success == 1;
    }

    public List<Rating> getAllByUserEmail(final String userEmail) {
        final String sql = "SELECT * FROM `movies_user_ratings` " +
                "WHERE `user_id` = (SELECT u.id FROM `users` AS u WHERE u.email='?')";
        return getJdbcOperations().query(sql, new Object[]{userEmail}, new RatingRowMapper());
    }

    public List<Rating> getAllByMovieTitle(final String movieTitle) {
        final String sql = "SELECT * FROM `movies_user_ratings` " +
                "WHERE `movie_id` = (SELECT m.id FROM `movies` AS m WHERE m.title='?')";
        return getJdbcOperations().query(sql, new Object[]{movieTitle}, new RatingRowMapper());
    }
}
