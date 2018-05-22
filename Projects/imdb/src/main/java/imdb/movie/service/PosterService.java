package imdb.movie.service;

import imdb.movie.dao.PosterDao;
import imdb.movie.model.Poster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosterService {

    private final PosterDao posterDao;

    @Autowired
    public PosterService(final PosterDao posterDao) {
        this.posterDao = posterDao;
    }

    public boolean add(final Poster poster, final long movieId) {
        return this.posterDao.add(poster, movieId);
    }

    public List<Poster> findAllByMovieId(final long movieId) {
        return this.posterDao.getAllByMovieId(movieId);
    }
}
