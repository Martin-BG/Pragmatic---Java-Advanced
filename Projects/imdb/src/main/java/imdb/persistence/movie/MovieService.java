package imdb.persistence.movie;

import imdb.model.Movie;
import imdb.persistence.actor.ActorService;
import imdb.persistence.rating.RatingService;
import imdb.persistence.user_movies.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieDao movieDao;
    private final GenreDao genreDao;
    private final PosterDao posterDao;
    private final TrailerDao trailerDao;
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
                        final ActorService actorService,
                        final RatingService ratingService,
                        final MovieActorsDao movieActorsDao,
                        final MovieGenresDao movieGenresDao,
                        final UserMoviesService userMoviesService) {
        this.movieDao = movieDao;
        this.genreDao = genreDao;
        this.posterDao = posterDao;
        this.trailerDao = trailerDao;
        this.actorService = actorService;
        this.ratingService = ratingService;
        this.movieActorsDao = movieActorsDao;
        this.movieGenresDao = movieGenresDao;
        this.userMoviesService = userMoviesService;
    }

    public boolean add(final String title, final Integer year, final String owner) {
        Movie movie = new Movie(title, year);
        movie.setOwner(owner);
        return this.add(movie);
    }

    public boolean add(final Movie movie) {
        if (!this.movieDao.add(movie)) {
            return false;
        }

        if (movie.getOwner() != null) {
            this.userMoviesService.add(movie.getTitle(), movie.getOwner());
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
        movie.setActors(new HashSet<>(this.movieActorsDao.getActorsForMovie(title)));
        movie.setGenres(new HashSet<>(this.movieGenresDao.getGenresForMovie(title)));
        initRating(movie);
        initOwner(movie);

        return movie;
    }

    public List<String> getAllTitles() {
        return this.movieDao.getAllTitles();
    }

    public List<Movie> getAllMovies() {
        return this.getAllTitles()
                .stream()
                .map(this::findByTitle)
                .collect(Collectors.toList());
    }

    public List<Movie> findByCriteria(final String param, final String value) {
        final StringBuilder query = new StringBuilder("SELECT m.title FROM `movies` AS m ");
        final StringBuilder filter = new StringBuilder("WHERE 1 = 1 ");
        boolean isLikeTypeSearch = false;

        switch (param) {
        case "title":
            filter.append("AND m.title LIKE ? ");
            isLikeTypeSearch = true;
            break;
        case "year":
            filter.append("AND m.year = ? ");
            break;
        case "actor":
            query.append("LEFT JOIN `movies_actors` AS ma ON ma.movie_id = m.id " +
                    "LEFT JOIN `actors` AS a ON a.id = ma.actor_id ");
            filter.append("AND a.name LIKE ? ");
            isLikeTypeSearch = true;
            break;
        case "genre":
            query.append("LEFT JOIN `movies_genres` AS mg ON mg.movie_id = m.id " +
                    "LEFT JOIN `genres` AS g ON g.id = mg.genre_id ");
            filter.append("AND g.name = ? ");
            break;
        case "user":
            query.append("LEFT JOIN `users_movies` AS um ON um.movie_id = m.id " +
                    "LEFT JOIN `users` AS u ON u.id = um.user_id "
            );
            filter.append("AND u.email LIKE ? ");
            isLikeTypeSearch = true;
            break;
        default:    // Invalid search criteria
            return new ArrayList<>();
        }

        query.append(filter)
                .append("GROUP BY m.id;");

        return this.movieDao
                .findByCriteria(query.toString(), isLikeTypeSearch ? ("%" + value + "%") : value)
                .stream()
                .map(this::findByTitle)
                .collect(Collectors.toList());
    }
}
