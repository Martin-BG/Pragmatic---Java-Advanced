package imdb.persistence.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
class PosterDao extends NamedParameterJdbcTemplate {

    @Autowired
    PosterDao(final DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    final boolean add(final String title, final String url) {
        final String sql = "INSERT INTO `posters` (`movie_id`, `url`) VALUES (" +
                "(SELECT m.id FROM `movies` AS m WHERE m.title = ?), ?)";
        try {
            return 1 == getJdbcOperations().update(sql, title, url);
        } catch (DataAccessException e) {
            return false;
        }
    }

    final Collection<String> getPostersForMovie(final String title) {
        final String sql = "SELECT p.url FROM `posters` AS p " +
                "WHERE p.movie_id = (SELECT m.id FROM `movies` AS m WHERE m.title = ?)";
        return getJdbcOperations().queryForList(sql, new Object[]{title}, String.class);
    }
}
