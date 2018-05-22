package imdb.user.model;

import imdb.movie.model.Movie;
import lombok.Data;

import java.util.Set;

@Data
public class User {

    private int id;
    private String email;
    private String password;
    private Set<Movie> userMovies;
    private Set<Rating> ratings;
}
