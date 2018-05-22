package imdb.user.model;

import imdb.movie.model.Movie;

import java.util.Set;

public class User {

    private String email;
    private String password;
    private Set<Movie> userMovies;
    private Set<Rating> ratings;
}
