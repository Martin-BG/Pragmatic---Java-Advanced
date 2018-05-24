package imdb.movie;

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

    boolean add(final String genre) {
        String sql = "INSERT INTO `genres` (`name`) VALUES (?)";
        try {
            return 1 == getJdbcOperations().update(sql, genre);
        } catch (DataAccessException e) {
            return false;
        }
    }

    boolean exists(final String genre) {
        String sql = "SELECT g.name FROM `genres` AS g WHERE g.name = ?";
        return genre.equals(getJdbcOperations().queryForObject(sql, new Object[]{genre}, String.class));
    }

    List<String> getGenresForMovie(final String title) {
        final String sql = "SELECT g.name FROM `genres` AS g " +
                "WHERE p.movie_id = (SELECT m.id FROM `movies` AS m WHERE m.title = ?)";
        return getJdbcOperations().queryForList(sql, new Object[]{title}, String.class);
    }

    List<String> getAllGenres() {
        final String sql = "SELECT g.name FROM `genres` AS g";
        return getJdbcOperations().queryForList(sql, String.class);
    }
}
