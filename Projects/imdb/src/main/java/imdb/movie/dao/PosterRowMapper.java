package imdb.movie.dao;

import imdb.movie.model.Poster;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PosterRowMapper implements RowMapper<Poster> {
    public Poster mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Poster poster = new Poster();
        poster.setUrl(rs.getString("url"));
        return poster;
    }
}
