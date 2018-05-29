package imdb;

import imdb.model.Movie;
import imdb.persistence.movie.MovieService;
import imdb.persistence.rating.RatingService;
import imdb.persistence.user.UserService;
import imdb.persistence.user_movies.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class Terminal implements CommandLineRunner {

    private final RatingService ratingService;
    private final UserService userService;
    private final UserMoviesService userMoviesService;
    private final MovieService movieService;

    @Autowired
    public Terminal(final RatingService ratingService,
                    final UserService userService,
                    final UserMoviesService userMoviesService,
                    final MovieService movieService) {
        this.ratingService = ratingService;
        this.userService = userService;
        this.userMoviesService = userMoviesService;
        this.movieService = movieService;
    }

    @Override
    public void run(final String... args) {
        System.out.println("RATING SERVICE DEMO:");
        ratingDemo();

        System.out.println("USER SERVICE DEMO:");
        userDemo();

        System.out.println("USER-MOVIES SERVICE DEMO:");
        userMoviesDemo();

        System.out.println("MOVIES MAIN DEMO:");
        moviesMainDemo();

        System.out.println("MOVIES SEARCH DEMO:");
        searchDemo();
    }

    private void searchDemo() {
        System.out.println("Action movies [genre]:");
        this.movieService.findByCriteria("genre", "action").forEach(System.out::println);

        System.out.println("'Dunk' [partial title] movies:");
        this.movieService.findByCriteria("title", "Dunk").forEach(System.out::println);

        System.out.println("2017 year movies:");
        this.movieService.findByCriteria("year", "2017").forEach(System.out::println);

        System.out.println("'Hanks' [actor partial name] movies:");
        this.movieService.findByCriteria("actor", "Hanks").forEach(System.out::println);

        System.out.println("'pesho' [user partial name] movies:");
        this.movieService.findByCriteria("user", "pesho").forEach(System.out::println);
    }

    private void moviesMainDemo() {
        System.out.println("Find valid movie: " + this.movieService.findByTitle("Dunkirk"));
        System.out.println("Find invalid movie: " + this.movieService.findByTitle("ZDunkirk"));
        Movie movie = this.movieService.findByTitle("12 Strong");
        System.out.println("Add existing movie: " + this.movieService.add(movie));
        movie = new Movie("Dummy Movie", 2018);
        movie.setOwner("pesho@abv.bg");
        movie.setActors(new HashSet<>(Arrays.asList("Toto", "Bobo", "Koko", "Chocho")));
        movie.setGenres(new HashSet<>(Arrays.asList("Undefined", "Unbearable", "Drama", "Comedy")));
        movie.setTrailers(new HashSet<>((Arrays.asList("http://unreachable.com/unknown", "localhost://temp/you-should-access-that-too"))));
        movie.setPosters(new HashSet<>(Arrays.asList("Desktop/pictureOfMe.jpg", "https://noaccess/koko.png")));
        System.out.println("Adding new movie: " + this.movieService.add(movie));
        System.out.println("The movie itself: " + this.movieService.findByTitle("Dummy Movie"));
    }

    private void userMoviesDemo() {
        System.out.println("Get titles for valid user: " + this.userMoviesService.getUserTitles("pesho@abv.bg"));
        System.out.println("Get titles for invalid user: " + this.userMoviesService.getUserTitles("Zpesho@abv.bg"));
        System.out.println("Get owner for valid movie: " + this.userMoviesService.getMovieOwner("Dunkirk"));
        System.out.println("Get owner for invalid movie: " + this.userMoviesService.getMovieOwner("ZDunkirk"));
        System.out.println("Add existing movie+user: " + this.userMoviesService.add("Dunkirk", "pesho@abv.bg"));
        System.out.println("Add invalid movie: " + this.userMoviesService.add("ZDunkirk", "pesho@abv.bg"));
        System.out.println("Add invalid user: " + this.userMoviesService.add("Dunkirk", "Zpesho@abv.bg"));
    }

    private void ratingDemo() {
        System.out.println(ratingService.get("Dunkirk", "pesho@abv.bg"));
        System.out.println("Deleted existing: " + ratingService.delete("Dunkirk", "pesho@abv.bg"));
        System.out.println("Deleted non-existent: " + ratingService.delete("Dunkirk", "pesho@abv.bg"));
        System.out.println("Update non-existent: " + ratingService.update("Dunkirk", "pesho@abv.bg", 6));
        System.out.println("Add new: " + ratingService.add("Dunkirk", "pesho@abv.bg", 6));
        System.out.println("Get existing: " + ratingService.get("Dunkirk", "pesho@abv.bg"));
        System.out.println("Update existing: " + ratingService.update("Dunkirk", "pesho@abv.bg", 3));
        System.out.println("Get existing: " + ratingService.get("Dunkirk", "pesho@abv.bg"));
        System.out.println("Add existing: " + ratingService.add("Dunkirk", "pesho@abv.bg", 9));
        System.out.println("Find for valid user: " + ratingService.getUserRatings("pesho@abv.bg"));
        System.out.println("Find for invalid user: " + ratingService.getUserRatings("momo"));
        System.out.println("Find for valid movie: " + ratingService.getMovieRatings("Dunkirk"));
        System.out.println("Find for invalid movie: " + ratingService.getMovieRatings("nono"));
    }

    private void userDemo() {
        System.out.println("Find invalid user: " + userService.find("dodo@koko.com"));
        System.out.println("Register new user: " + userService.register("dodo@koko.com", "dodo"));
        System.out.println("Find valid user: " + userService.find("dodo@koko.com"));
        System.out.println("Validate valid user + password: " + userService.areCredentialsValid("dodo@koko.com", "dodo"));
        System.out.println("Validate valid user + invalid password: " + userService.areCredentialsValid("dodo@koko.com", "dodo3"));
        System.out.println("Validate invalid user + password: " + userService.areCredentialsValid("dodo@koko.co", "dodo"));
        System.out.println("Valid user & movie rating: " + userService.getUserRatingForMovie("Dunkirk", "pesho@abv.bg"));
        System.out.println("Invalid user & valid movie rating: " + userService.getUserRatingForMovie("Dunkirk", "Zpesho@abv.bg"));
        System.out.println("Valid user & invalid movie rating: " + userService.getUserRatingForMovie("DunkirkZZ", "pesho@abv.bg"));
        System.out.println("Invalid user & movie rating: " + userService.getUserRatingForMovie("DunkirkZZ", "Zpesho@abv.bg"));
        System.out.println("Valid user titles: " + userService.getUserTitles("pesho@abv.bg"));
        System.out.println("Invalid user titles: " + userService.getUserTitles("Zpesho@abv.bg"));
        System.out.println("Valid user ratings: " + userService.getUserRatings("pesho@abv.bg"));
        System.out.println("Invalid user ratings: " + userService.getUserRatings("Zpesho@abv.bg"));
    }
}
