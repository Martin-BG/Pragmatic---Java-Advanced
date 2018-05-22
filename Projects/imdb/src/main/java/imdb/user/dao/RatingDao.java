package imdb.user.dao;

import imdb.user.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RatingDao extends NamedParameterJdbcTemplate {

    @Autowired
    public RatingDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(final Rating rating, final long userId) {
        final String sql = "INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) VALUES (?, ?, ?)";
        int success = getJdbcOperations().update(sql, rating.getMovie().getId(), userId, rating.getRating());
        return success == 1;
    }

    public List<Rating> getAllByUserId(final long userId) {
        final String sql = "SELECT * FROM `movies_user_ratings` WHERE `user_id` = ?";
        return getJdbcOperations().query(sql, new Object[]{userId}, new RatingRowMapper());
    }

    public List<Rating> getAllByMovieId(final long movieId) {
        final String sql = "SELECT * FROM `movies_user_ratings` WHERE `movie_id` = ?";
        return getJdbcOperations().query(sql, new Object[]{movieId}, new RatingRowMapper());
    }
}
