package imdb.user.service;

import imdb.movie.dao.MovieDao;
import imdb.movie.model.Movie;
import imdb.user.dao.RatingDao;
import imdb.user.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class RatingService {

    private final RatingDao ratingDao;
    private final MovieDao movieDao;

    @Autowired
    public RatingService(final RatingDao ratingDao,
                         final MovieDao movieDao) {
        this.ratingDao = ratingDao;
        this.movieDao = movieDao;
    }

    public boolean add(final Rating rating, final long userId) {
        return this.ratingDao.add(rating, userId);
    }

    public List<Rating> getAllByUserId(final long userId) {
        final List<Rating> ratings = this.ratingDao.getAllByUserId(userId);

        final Iterator<Rating> iterator = ratings.iterator();
        while (iterator.hasNext()) {
            final Rating rating = iterator.next();
            final Movie movie = this.movieDao.findById(rating.getMovie().getId());
            if (movie == null) {
                iterator.remove();
            }
            rating.setMovie(movie);
        }

        return ratings;
    }

    public List<Rating> getAllByMovieId(final long movieId) {
        return this.ratingDao.getAllByMovieId(movieId);
    }
}
