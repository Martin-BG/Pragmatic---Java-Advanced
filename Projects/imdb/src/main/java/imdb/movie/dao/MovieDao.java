package imdb.movie.dao;

import imdb.movie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MovieDao extends NamedParameterJdbcTemplate {

    @Autowired
    public MovieDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(Movie movie) {
        String sql = "INSERT INTO `movies` (`title`, `year`) VALUES (?, ?)";
        int success = getJdbcOperations().update(movie.getTitle(), movie.getYear());
        return success == 1;
    }
}
