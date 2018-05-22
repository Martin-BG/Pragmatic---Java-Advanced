package imdb.movie.dao;

import imdb.movie.model.Movie;
import imdb.movie.model.Trailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TrailerDao extends NamedParameterJdbcTemplate {

    @Autowired
    public TrailerDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(Trailer trailer, Movie movie) {
        String sql = "INSERT INTO `trailers` (`movie_id`, `url`) VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, movie.getId(), trailer.getUrl());
        return success == 1;
    }

    public List<Trailer> getAllByMovieId(final long movieId) {
        final String sql = "SELECT * FROM `trailers` WHERE `movie_id` = ?";
        return getJdbcOperations().query(sql, new Object[]{movieId}, new TrailerRowMapper());
    }
}
