package imdb.controller;

import imdb.config.Messages;
import imdb.model.Movie;
import imdb.persistence.movie.MovieService;
import imdb.persistence.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@Controller
public final class UserController {

    private final UserService userService;
    private final MovieService movieService;
    private final LoggedUser loggedUser;
    private final Messages messages;

    @Autowired
    public UserController(final UserService userService,
                          final MovieService movieService,
                          final LoggedUser loggedUser,
                          final Messages messages) {
        this.userService = userService;
        this.movieService = movieService;
        this.loggedUser = loggedUser;
        this.messages = messages;
    }

    @RequestMapping(value = {"/register", "/register.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerUser(final ModelAndView view,
                                     @RequestParam(name = "email", defaultValue = "") final String email,
                                     @RequestParam(name = "password", defaultValue = "") final String password,
                                     @RequestParam(name = "passwordConfirm", defaultValue = "") final String passwordConfirm) {
        if (email.isEmpty() && password.isEmpty() && passwordConfirm.isEmpty()) {
            view.setViewName("/register");
        } else if (email.isEmpty()) {
            view.getModel().put("message", this.messages.get("email.empty"));
            view.setViewName("/register");
        } else if (password.isEmpty() || !password.equals(passwordConfirm)) {
            view.getModel().put("message", this.messages.get("password.mismatch"));
            view.setViewName("/register");
        } else {
            if (this.userService.register(email, password)) {
                this.loggedUser.loginUser(email);
                view.setViewName("redirect:/index");
            } else {
                view.getModel().put("message", this.messages.get("registration.failed"));
                view.setViewName("/register");
            }
        }

        return view;
    }

    @RequestMapping(value = {"/login", "/login.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginUser(final ModelAndView view,
                                  final RedirectAttributes redirectAttributes,
                                  @RequestParam(name = "email", required = false, defaultValue = "") final String email,
                                  @RequestParam(name = "password", required = false, defaultValue = "") final String password) {
        if (!this.loggedUser.isNotLogged()) {
            redirectAttributes.addFlashAttribute("message", this.messages.get("logout.first"));
            view.setViewName("redirect:/index");
        } else if (email.isEmpty() || password.isEmpty()) {
            view.setViewName("/login");
        } else if (!this.userService.areCredentialsValid(email, password)) {
            view.getModel().put("message", this.messages.get("invalid.credentials"));
            view.setViewName("/login");
        } else {
            this.loggedUser.loginUser(email);
            view.setViewName("redirect:/index");
        }

        return view;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/", "/index", "/index.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userHome(final ModelAndView view,
                                 final HttpServletRequest request) {

        final Map<String, Object> attribute = (Map<String, Object>)
                request.getAttribute("org.springframework.web.servlet.DispatcherServlet.INPUT_FLASH_MAP");
        // Add current user's or top rated movies if "movies" attribute is not present in request
        if (attribute == null || !attribute.containsKey("movies")) {
            final Collection<Movie> movies = this.loggedUser.isNotLogged() ?
                    this.movieService.getTopRated(10, 0.0d) :
                    this.movieService.findByCriteria("owner", this.loggedUser.getLoggedUserIdentity());
            view.getModel().put("movies", movies);
        }

        if (!this.loggedUser.isNotLogged()) {
            view.getModel().put("username", this.loggedUser.getLoggedUserIdentity());
        }

        view.setViewName("/index");

        return view;
    }

    @RequestMapping(value = {"/logout", "/logout.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView logoutUser(final ModelAndView view,
                                   final RedirectAttributes redirectAttributes) {
        if (this.loggedUser.isNotLogged()) {
            redirectAttributes.addFlashAttribute("message", this.messages.get("login.required"));
        } else {
            redirectAttributes.addFlashAttribute("message",
                    this.messages.get("logout.user") + this.loggedUser.getLoggedUserIdentity());
            this.loggedUser.logoutUser();
        }

        view.setViewName("redirect:/index");
        return view;
    }
}
