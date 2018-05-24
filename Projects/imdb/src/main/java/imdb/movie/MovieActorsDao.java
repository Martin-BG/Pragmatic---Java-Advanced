package imdb.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MovieActorsDao extends NamedParameterJdbcTemplate {

    @Autowired
    MovieActorsDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final String title, final String actor) {
        final String sql = "INSERT INTO `movies_actors` (`movie_id`, `actor_id`) " +
                "VALUES ((SELECT m.id FROM `movies` AS m WHERE m.title = ?), " +
                "(SELECT a.id FROM `actors` AS a WHERE a.name = ?))";
        try {
            return 1 == getJdbcOperations().update(sql, title, actor);
        } catch (DataAccessException e) {
            return false;
        }
    }

    Set<String> getActorssForMovie(final String title) {
        final String sql = "SELECT a.name FROM `movies_actors` AS ma " +
                "LEFT JOIN `actors` AS a ON a.id = ma.actor_id " +
                "WHERE ma.movie_id = (SELECT m.id FROM `movies` AS m WHERE m.title = ?)";
        return new HashSet<>(getJdbcOperations().queryForList(sql, new Object[]{title}, String.class));
    }
}
