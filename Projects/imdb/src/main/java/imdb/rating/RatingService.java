package imdb.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class RatingService {

    private final RatingDao ratingDao;

    @Autowired
    public RatingService(final RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    public boolean add(final String movieTitle, final String userEmail, final int rating) {
        return this.ratingDao.add(movieTitle, userEmail, rating);
    }

    public boolean update(final String movieTitle, final String userEmail, final int rating) {
        return this.ratingDao.update(movieTitle, userEmail, rating);
    }

    public boolean delete(final String movieTitle, final String userEmail) {
        return this.ratingDao.delete(movieTitle, userEmail);
    }

    public Integer get(final String movieTitle, final String userEmail) {
        return this.ratingDao.get(movieTitle, userEmail);
    }

    public List<String> getUserRatings(final String userEmail) {
        return this.ratingDao.getAllForUser(userEmail);
    }

    public List<Integer> getMovieRatings(final String movieTitle) {
        return this.ratingDao.getAllForMovie(movieTitle);
    }
}
