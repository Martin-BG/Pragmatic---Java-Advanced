package imdb.persistence.movie;

import imdb.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
class MovieDao extends NamedParameterJdbcTemplate {

    @Autowired
    MovieDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final Movie movie) {
        String sql = "INSERT INTO `movies` (`title`, `year`) VALUES (?, ?)";
        try {
            return 1 == getJdbcOperations().update(sql, movie.getTitle(), movie.getYear());
        } catch (DataAccessException e) {
            return false;
        }
    }

    Movie findByTitle(final String title) {
        final String sql = "SELECT * FROM `movies` WHERE `title` = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{title}, new MovieRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    List<String> getAllTitles() {
        final String sql = "SELECT m.title FROM `movies` AS m";
        return getJdbcOperations().queryForList(sql, String.class);
    }
}