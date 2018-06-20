package imdb.persistence.user;

import imdb.model.User;
import imdb.persistence.rating.RatingService;
import imdb.persistence.usermovies.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final public class UserService {

    private final UserDao userDao;
    private final RatingService ratingService;
    private final UserMoviesService userMoviesService;

    @Autowired
    public UserService(final UserDao userDao,
                       final RatingService ratingService,
                       final UserMoviesService userMoviesService) {
        this.userDao = userDao;
        this.ratingService = ratingService;
        this.userMoviesService = userMoviesService;
    }

    public boolean register(final String email, final String password) {
        return this.userDao.add(email, password);
    }

    public User find(final String email) {
        return this.userDao.get(email);
    }

    public Integer getUserRatingForMovie(final String title, final String email) {
        return this.ratingService.get(title, email);
    }

    public List<String> getUserRatings(final String email) {
        return this.ratingService.getUserRatings(email);
    }

    public List<String> getUserTitles(final String email) {
        return this.userMoviesService.getUserTitles(email);
    }

    public boolean areCredentialsValid(final String email, final String password) {
        final User user = this.userDao.get(email);
        return user != null && user.getPassword().equals(password);
    }
}
