package imdb.movie.service;

import imdb.movie.dao.TrailerDao;
import imdb.movie.model.Trailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailerService {

    private final TrailerDao trailerDao;

    @Autowired
    public TrailerService(final TrailerDao trailerDao) {
        this.trailerDao = trailerDao;
    }

    public boolean add(final Trailer poster, final long movieId) {
        return this.trailerDao.add(poster, movieId);
    }

    public List<Trailer> findAllByMovieId(final long movieId) {
        return this.trailerDao.getAllByMovieId(movieId);
    }
}
