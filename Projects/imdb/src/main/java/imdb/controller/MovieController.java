package imdb.controller;

import imdb.config.Messages;
import imdb.persistence.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final LoggedUser loggedUser;
    private final Messages messages;

    @Autowired
    public MovieController(final MovieService movieService,
                           final LoggedUser loggedUser,
                           final Messages messages) {
        this.movieService = movieService;
        this.loggedUser = loggedUser;
        this.messages = messages;
    }


}
