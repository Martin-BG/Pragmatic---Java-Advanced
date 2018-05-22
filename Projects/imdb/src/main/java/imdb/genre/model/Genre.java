package imdb.genre.model;

import imdb.movie.model.Movie;
import lombok.Data;

import java.util.Set;

@Data
public class Genre {

    private String name;
    private Set<Movie> movies;
}
