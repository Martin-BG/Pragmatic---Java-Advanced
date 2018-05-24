package imdb.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public final class RatingService {

    private final RatingDao ratingDao;

    @Autowired
    public RatingService(final RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    public boolean add(final Rating rating) {
        return this.ratingDao.add(rating);
    }

    public boolean update(final Rating rating) {
        return this.ratingDao.update(rating);
    }

    public boolean delete(final Rating rating) {
        return this.ratingDao.delete(rating);
    }

    public Rating get(final String userEmail, final String movieTitle) {
        return this.ratingDao.get(userEmail, movieTitle);
    }

    public Set<Rating> getUserRatings(final String userEmail) {
        return this.ratingDao.getAllForUser(userEmail);
    }

    public Set<Rating> getMovieRatings(final String movieTitle) {
        return this.ratingDao.getAllForMovie(movieTitle);
    }
}
