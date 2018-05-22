package imdb.user.dao;

import imdb.movie.model.Movie;
import imdb.user.model.Rating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper implements RowMapper<Rating> {
    public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("movie_id"));
        Rating rating = new Rating();
        rating.setRating(rs.getDouble("rating"));
        rating.setMovie(movie);
        return rating;
    }
}
