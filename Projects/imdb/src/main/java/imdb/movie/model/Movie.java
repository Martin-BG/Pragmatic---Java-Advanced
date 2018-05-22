package imdb.movie.model;

import imdb.actor.model.Actor;

import java.util.Set;

public class Movie {

    private String title;
    private int year;
    private Set<Genre> genres;
    private Set<Actor> actors;
    private Set<Poster> posters;
    private Set<Trailer> trailers;
    private int votes;
    private double rating;
}
