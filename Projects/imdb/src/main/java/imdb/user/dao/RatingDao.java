package imdb.user.dao;

import imdb.user.model.Rating;
import imdb.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class RatingDao extends NamedParameterJdbcTemplate {

    @Autowired
    public RatingDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(Rating rating, User user) {
        String sql = "INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) VALUES (?, ?, ?)";
        int success = getJdbcOperations().update(sql, rating.getMovie().getId(), user.getId(), rating.getRating());
        return success == 1;
    }
}
