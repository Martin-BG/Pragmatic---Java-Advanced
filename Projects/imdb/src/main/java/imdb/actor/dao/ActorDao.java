package imdb.actor.dao;

import imdb.actor.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ActorDao extends NamedParameterJdbcTemplate {

    @Autowired
    public ActorDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(Actor actor) {
        String sql = "INSERT INTO `actors` (`name`) VALUES (?)";
        int success = getJdbcOperations().update(sql, actor.getName());
        return success == 1;
    }


    public Actor findByName(final String name) {
        final String sql = "SELECT * FROM `actors` WHERE `name` = ?";
        try {
            return getJdbcOperations().queryForObject(sql, new Object[]{name}, new ActorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
