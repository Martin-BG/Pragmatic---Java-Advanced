package imdb.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public final class Movie {

    private final String title;
    private final Integer year;
    private String owner;
    private Integer votes;
    private Double rating;
    private Set<String> genres;
    private Set<String> actors;
    private Set<String> posters;
    private Set<String> trailers;

    public Movie(final String title, final int year) {
        this.title = title;
        this.year = year;
        this.owner = null;
        this.votes = 0;
        this.rating = 0.0;
        this.genres = new HashSet<>();
        this.actors = new HashSet<>();
        this.posters = new HashSet<>();
        this.trailers = new HashSet<>();
    }
}
