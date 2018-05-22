package imdb.movie.service;

import imdb.movie.dao.MovieDao;
import imdb.movie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieService(final MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public boolean add(final Movie movie) {
        return this.movieDao.add(movie);
    }

    public Movie findByMovieId(final long movieId) {
        return this.movieDao.findById(movieId);
    }

    public Movie findByTitle(final String title) {
        return this.movieDao.findByTitle(title);
    }
}
