package imdb.movie.dao;

import imdb.movie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MovieDao extends NamedParameterJdbcTemplate {

    @Autowired
    public MovieDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(final Movie movie) {
        String sql = "INSERT INTO `movies` (`title`, `year`) VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, movie.getTitle(), movie.getYear());
        return success == 1;
    }

    public Movie findById(final long movieId) {
        final String sql = "SELECT * FROM `movies` WHERE `id` = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{movieId}, new MovieRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Movie findByTitle(final String title) {
        final String sql = "SELECT * FROM `movies` WHERE `title` = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{title}, new MovieRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
