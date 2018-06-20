package imdb.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = {"title", "year"})
public final class Movie {

    private final String title;
    private final Integer year;
    private String owner;
    private Integer votes;
    private Double rating;
    private Collection<String> genres;
    private Collection<String> actors;
    private Collection<String> posters;
    private Collection<String> trailers;

    public Movie(final String title, final int year) {
        this.title = title;
        this.year = year;
        this.owner = null;
        this.votes = 0;
        this.rating = 0.0;
        this.setGenres(null);
        this.setActors(null);
        this.setPosters(null);
        this.setTrailers(null);
    }

    private Collection<String> toCollection(final Collection<String> entries) {
        if (entries == null) {
            return new LinkedHashSet<>();
        }

        return entries.stream()
                .collect(Collectors.toMap(
                        String::toLowerCase,    // Case insensitive distinct
                        entry -> entry,
                        (e1, e2) -> e1,         // Keep first entry
                        LinkedHashMap::new))    // Preserve input order
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Collection<String> getGenres() {
        return Collections.unmodifiableCollection(this.genres);
    }

    public void setGenres(final Collection<String> genres) {
        this.genres = this.toCollection(genres);
    }

    public Collection<String> getActors() {
        return Collections.unmodifiableCollection(this.actors);
    }

    public void setActors(final Collection<String> actors) {
        this.actors = this.toCollection(actors);
    }

    public Collection<String> getPosters() {
        return Collections.unmodifiableCollection(this.posters);
    }

    public void setPosters(final Collection<String> posters) {
        this.posters = this.toCollection(posters);
    }

    public Collection<String> getTrailers() {
        return Collections.unmodifiableCollection(this.trailers);
    }

    public void setTrailers(final Collection<String> trailers) {
        this.trailers = this.toCollection(trailers);
    }
}
