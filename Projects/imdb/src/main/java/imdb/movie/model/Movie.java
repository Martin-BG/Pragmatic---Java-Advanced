package imdb.movie.model;

import imdb.actor.model.Actor;
import imdb.genre.model.Genre;
import lombok.Data;

import java.util.Set;

@Data
public class Movie {

    private long id;
    private String title;
    private int year;
    private int votes;
    private double rating;
    private Set<Genre> genres;
    private Set<Actor> actors;
    private Set<Poster> posters;
    private Set<Trailer> trailers;
}
