package imdb.actor.model;

import imdb.movie.model.Movie;
import lombok.Data;

import java.util.Set;

@Data
public class Actor {

    private String name;
    private Set<Movie> movies;
}
