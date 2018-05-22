package imdb.movie.dao;

import imdb.movie.model.Trailer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrailerRowMapper implements RowMapper<Trailer> {
    public Trailer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Trailer trailer = new Trailer();
        trailer.setUrl(rs.getString("url"));
        return trailer;
    }
}
