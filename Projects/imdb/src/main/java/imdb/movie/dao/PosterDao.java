package imdb.movie.dao;

import imdb.movie.model.Poster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PosterDao extends NamedParameterJdbcTemplate {

    @Autowired
    public PosterDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(final Poster poster, final long movieId) {
        String sql = "INSERT INTO `posters` (`movie_id`, `url`) VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, movieId, poster.getUrl());
        return success == 1;
    }

    public List<Poster> getAllByMovieId(final long movieId) {
        final String sql = "SELECT * FROM `posters` WHERE `movie_id` = ?";
        return getJdbcOperations().query(sql, new Object[]{movieId}, new PosterRowMapper());
    }
}
