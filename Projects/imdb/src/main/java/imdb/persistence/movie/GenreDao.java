package imdb.persistence.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
class GenreDao extends NamedParameterJdbcTemplate {

    @Autowired
    GenreDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    final boolean add(final String genre) {
        final String sql = "INSERT INTO `genres` (`name`) VALUES (?)";
        try {
            return 1 == getJdbcOperations().update(sql, genre);
        } catch (DataAccessException e) {
            return false;
        }
    }

    final boolean exists(final String genre) {
        final String sql = "SELECT g.name FROM `genres` AS g WHERE g.name = ?";
        return genre.equals(getJdbcOperations().queryForObject(sql, new Object[]{genre}, String.class));
    }

    final List<String> getAllGenres() {
        final String sql = "SELECT g.name FROM `genres` AS g";
        return getJdbcOperations().queryForList(sql, String.class);
    }
}
