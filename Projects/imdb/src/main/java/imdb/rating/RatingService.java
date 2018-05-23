package imdb.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingDao ratingDao;

    @Autowired
    public RatingService(final RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    public boolean add(final Rating rating, final String userEmail) {
        return this.ratingDao.add(rating, userEmail);
    }

    public List<Rating> getRatingsForUser(final String userEmail) {
        return this.ratingDao.getAllByUserEmail(userEmail);
    }

    public List<Rating> getRatingsForMovie(final String movieTitle) {
        return this.ratingDao.getAllByMovieTitle(movieTitle);
    }
}
