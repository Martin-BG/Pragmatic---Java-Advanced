package imdb.movie.dao;

import imdb.movie.model.Movie;
import imdb.movie.model.Poster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PosterDao extends NamedParameterJdbcTemplate {

    @Autowired
    public PosterDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(Poster poster, Movie movie) {
        String sql = "INSERT INTO `posters` (`movie_id`, `url`) VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, movie.getId(), poster.getUrl());
        return success == 1;
    }
}
