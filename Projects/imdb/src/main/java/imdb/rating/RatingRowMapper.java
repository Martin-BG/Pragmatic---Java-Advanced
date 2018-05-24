package imdb.rating;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

final class RatingRowMapper implements RowMapper<Rating> {

    public Rating mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Rating(
                rs.getString("email"),
                rs.getString("title"),
                rs.getInt("rating"));
    }
}
