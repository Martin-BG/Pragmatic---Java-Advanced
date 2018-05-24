package imdb.rating;

import lombok.Data;

@Data
public final class Rating {

    private final String userEmail;
    private final String movieTitle;
    private final int rating;

    public Rating getNewRating(final int newRate) {
        return new Rating(this.userEmail, this.movieTitle, newRate);
    }
}
