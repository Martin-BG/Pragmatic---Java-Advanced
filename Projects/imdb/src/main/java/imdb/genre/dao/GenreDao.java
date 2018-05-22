package imdb.genre.dao;

import imdb.genre.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class GenreDao extends NamedParameterJdbcTemplate {

    @Autowired
    public GenreDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(Genre genre) {
        String sql = "INSERT INTO `genres` (`name`) VALUES (?)";
        int success = getJdbcOperations().update(sql, genre.getName());
        return success == 1;
    }
}
