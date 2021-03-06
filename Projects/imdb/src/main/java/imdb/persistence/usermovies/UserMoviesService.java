package imdb.persistence.usermovies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class UserMoviesService {

    private final UserMoviesDao userMoviesDao;

    @Autowired
    public UserMoviesService(final UserMoviesDao userMoviesDao) {
        this.userMoviesDao = userMoviesDao;
    }

    public boolean add(final String movieTitle, final String userEmail) {
        return this.userMoviesDao.add(movieTitle, userEmail);
    }

    public Collection<String> getUserTitles(final String userEmail) {
        return this.userMoviesDao.getAllForUser(userEmail);
    }

    public String getMovieOwner(final String movieTitle) {
        return this.userMoviesDao.getOwner(movieTitle);
    }
}
