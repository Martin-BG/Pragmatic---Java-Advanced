package imdb.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
class ActorDao extends NamedParameterJdbcTemplate {

    @Autowired
    ActorDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    boolean add(final String actor) {
        String sql = "INSERT INTO `actors` (`name`) VALUES (?)";
        try {
            return 1 == getJdbcOperations().update(sql, actor);
        } catch (DataAccessException e) {
            return false;
        }
    }
}
