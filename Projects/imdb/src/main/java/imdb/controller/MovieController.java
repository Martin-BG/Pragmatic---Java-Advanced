package imdb.controller;

import imdb.config.Messages;
import imdb.model.Movie;
import imdb.persistence.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = {"/add", "/add.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addMovie(final ModelAndView view,
                                 final RedirectAttributes redirectAttributes,
                                 @RequestParam(name = "title", defaultValue = "") final String title,
                                 @RequestParam(name = "year", defaultValue = "") final String year) {
        if (this.loggedUser.isNotLogged()) {
            redirectAttributes.addFlashAttribute("message", this.messages.get("login.required"));
            view.setViewName("redirect:/login");
        } else if (title.isEmpty() && year.isEmpty()) {
            view.setViewName("/add");
        } else if (!year.matches("\\d{4}") ||
                !this.movieService.add(title, Integer.parseInt(year), this.loggedUser.getLoggedUserIdentity())) {
            view.getModel().put("message", this.messages.get("movie.add-fail") + title);
            view.setViewName("/add");
        } else {
            redirectAttributes.addFlashAttribute("movie", this.movieService.findByTitle(title));
            redirectAttributes.addFlashAttribute("message", this.messages.get("movie.add-success"));
            view.setViewName("redirect:/edit");
        }

        if (!this.loggedUser.isNotLogged()) {
            view.getModel().put("username", this.loggedUser.getLoggedUserIdentity());
        }

        return view;
    }

    @RequestMapping(value = {"/edit", "/edit.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView editMovie(final ModelAndView view,
                                  final RedirectAttributes redirectAttributes,
                                  @RequestParam(name = "title", defaultValue = "") final String title,
                                  @ModelAttribute final Movie movie) {
        if (this.loggedUser.isNotLogged()) {
            redirectAttributes.addFlashAttribute("message", this.messages.get("login.required"));
            view.setViewName("redirect:/login");
        } else if (movie == null && title.isEmpty()) {
            view.setViewName("/edit");
        } else if (!title.isEmpty()) {
            // TODO - load movie for edit
            view.setViewName("/edit");
        } else {
            // TODO - update movie in DB
            view.setViewName("/edit");
        }

        if (!this.loggedUser.isNotLogged()) {
            view.getModel().put("username", this.loggedUser.getLoggedUserIdentity());
        }

        return view;
    }

    @RequestMapping(value = {"/search", "/search.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView searchMovies(final ModelAndView view,
                                     final RedirectAttributes redirectAttributes,
                                     @RequestParam(name = "param", defaultValue = "") final String param,
                                     @RequestParam(name = "value", defaultValue = "") final String value) {
        if (param.isEmpty()) {
            view.setViewName("/search");
        } else {
            final List<Movie> movies = this.movieService.findByCriteria(param, value);
            redirectAttributes.addFlashAttribute("movies", movies);
            redirectAttributes.addFlashAttribute("message",
                    this.messages.get("movie.search-result") + movies.size());

            view.setViewName("redirect:/index");
        }

        if (!this.loggedUser.isNotLogged()) {
            view.getModel().put("username", this.loggedUser.getLoggedUserIdentity());
        }

        return view;
    }

    @RequestMapping(value = {"/details", "/details.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView movieDetails(final ModelAndView view,
                                     final RedirectAttributes redirectAttributes,
                                     @RequestParam(name = "title", defaultValue = "") final String title) {
        final Movie movie = title.isEmpty() ? null : this.movieService.findByTitle(title);
        if (movie == null) {
            redirectAttributes.addFlashAttribute("message", this.messages.get("movie.not-found"));
            view.setViewName("redirect:/index");
        } else {
            view.getModel().put("movie", movie);
            view.setViewName("/details");
        }

        if (!this.loggedUser.isNotLogged()) {
            view.getModel().put("username", this.loggedUser.getLoggedUserIdentity());
        }

        return view;
    }
}
