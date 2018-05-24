package imdb.movie;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class MovieRowMapper implements RowMapper<Movie> {

    public Movie mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Movie(
                rs.getString("title"),
                rs.getInt("year"));
    }
}
