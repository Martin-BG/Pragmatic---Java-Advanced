package imdb.user.model;

import imdb.movie.model.Movie;
import lombok.Data;

@Data
public class Rating {

    private Movie movie;
    private double rating;
}
