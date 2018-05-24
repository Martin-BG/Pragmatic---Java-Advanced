package imdb.movie;

import lombok.Data;

import java.util.Set;

@Data
public class Movie {

    final private String title;
    final private int year;
    private int votes;
    private double rating;
    private String owner;
    private Set<String> genres;
    private Set<String> actors;
    private Set<String> posters;
    private Set<String> trailers;
}
