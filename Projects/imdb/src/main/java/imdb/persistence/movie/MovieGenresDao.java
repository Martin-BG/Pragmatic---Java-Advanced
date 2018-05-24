package imdb.persistence.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MovieGenresDao extends NamedParameterJdbcTemplate {

    @Autowired
    MovieGenresDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final String title, final String genre) {
        final String sql = "INSERT INTO `movies_genres` (`movie_id`, `genre_id`) " +
                "VALUES ((SELECT m.id FROM `movies` AS m WHERE m.title = ?), " +
                "(SELECT g.id FROM `genres` AS g WHERE g.name = ?))";
        try {
            return 1 == getJdbcOperations().update(sql, title, genre);
        } catch (DataAccessException e) {
            return false;
        }
    }

    Set<String> getGenresForMovie(final String title) {
        final String sql = "SELECT g.name FROM `movies_genres` AS mg " +
                "LEFT JOIN `genres` AS g ON g.id = mg.genre_id " +
                "WHERE mg.movie_id = (SELECT m.id FROM `movies` AS m WHERE m.title = ?)";
        return new HashSet<>(getJdbcOperations().queryForList(sql, new Object[]{title}, String.class));
    }
}
