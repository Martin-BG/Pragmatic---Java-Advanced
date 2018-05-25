package imdb.controller;

import imdb.config.Messages;
import imdb.model.Movie;
import imdb.persistence.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = {"/", "/home"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerUser(final ModelAndView view) {
        view.setViewName("/home");
        final List<Movie> movies = this.movieService.getAllMovies();
        view.getModel().put("movies", movies);
        view.addObject("message", "MESSAGE FROM BACK-END");
        return view;
    }
}
