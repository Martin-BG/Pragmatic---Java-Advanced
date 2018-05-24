package imdb.movie;

import imdb.actor.ActorService;
import imdb.rating.RatingService;
import imdb.user.UserService;
import imdb.user_movies.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class MovieService {

    private final MovieDao movieDao;
    private final GenreDao genreDao;
    private final PosterDao posterDao;
    private final TrailerDao trailerDao;
    private final UserService userService;
    private final ActorService actorService;
    private final RatingService ratingService;
    private final MovieActorsDao movieActorsDao;
    private final MovieGenresDao movieGenresDao;
    private final UserMoviesService userMoviesService;

    @Autowired
    public MovieService(final MovieDao movieDao,
                        final GenreDao genreDao,
                        final PosterDao posterDao,
                        final TrailerDao trailerDao,
                        final UserService userService,
                        final ActorService actorService,
                        final RatingService ratingService,
                        final MovieActorsDao movieActorsDao,
                        final MovieGenresDao movieGenresDao,
                        final UserMoviesService userMoviesService) {
        this.movieDao = movieDao;
        this.genreDao = genreDao;
        this.posterDao = posterDao;
        this.trailerDao = trailerDao;
        this.userService = userService;
        this.actorService = actorService;
        this.ratingService = ratingService;
        this.movieActorsDao = movieActorsDao;
        this.movieGenresDao = movieGenresDao;
        this.userMoviesService = userMoviesService;
    }

    public boolean add(final Movie movie) {
        if (!this.movieDao.add(movie)) {
            return false;
        }

        final String title = movie.getTitle();

        for (final String poster : movie.getPosters()) {
            this.posterDao.add(title, poster);
        }

        for (final String trailer : movie.getTrailers()) {
            this.trailerDao.add(title, trailer);
        }

        for (final String actor : movie.getActors()) {
            this.actorService.add(actor);
            this.movieActorsDao.add(title, actor);
        }

        for (final String genre : movie.getGenres()) {
            this.genreDao.add(genre);
            movieGenresDao.add(title, genre);
        }

        initRating(movie);

        initOwner(movie);

        return true;
    }

    private void initOwner(final Movie movie) {
        movie.setOwner(this.userMoviesService.getMovieOwner(movie.getTitle()));
    }

    private void initRating(final Movie movie) {
        final List<Integer> ratings = this.ratingService.getMovieRatings(movie.getTitle());
        movie.setVotes(ratings.size());
        movie.setRating((double) ratings.stream().mapToInt(x -> x).sum() / ratings.size());
    }

    public Movie findByTitle(final String title) {
        Movie movie = this.movieDao.findByTitle(title);
        if (movie == null) {
            return null;
        }

        movie.setTrailers(new HashSet<>(this.trailerDao.getTrailersForMovie(title)));
        movie.setPosters(new HashSet<>(this.posterDao.getPostersForMovie(title)));
        movie.setActors(new HashSet<>(this.movieActorsDao.getActorssForMovie(title)));
        movie.setGenres(new HashSet<>(this.movieGenresDao.getGenresForMovie(title)));
        initRating(movie);
        initOwner(movie);

        return movie;
    }
}
