package imdb.actor.dao;

import imdb.actor.model.Actor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorRowMapper implements RowMapper<Actor> {
    public Actor mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Actor actor = new Actor();
        actor.setName(rs.getString("name"));
        return actor;
    }
}
