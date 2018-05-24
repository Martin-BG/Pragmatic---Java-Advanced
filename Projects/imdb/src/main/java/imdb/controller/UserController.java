package imdb.controller;

import imdb.config.Messages;
import imdb.persistence.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;
    private final Messages messages;

    @Autowired
    public UserController(final UserService userService,
                          final LoggedUser loggedUser,
                          final Messages messages) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.messages = messages;
    }

    @RequestMapping(value = {"/user-register"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerUser(final ModelAndView view,
                                     @RequestParam(name = "email", defaultValue = "") final String email,
                                     @RequestParam(name = "password", defaultValue = "") final String password,
                                     @RequestParam(name = "passwordConfirm", defaultValue = "") final String passwordConfirm) {
        if (email.isEmpty() || password.isEmpty() || !password.equals(passwordConfirm)) {
            if (!password.equals(passwordConfirm)) {
                view.addObject("message", this.messages.get("password.mismatch"));
            }
            view.setViewName("/user-register");
        } else {
            if (this.userService.register(email, password)) {
                this.loggedUser.loginUser(email);
                view.addObject("name", this.loggedUser.getLoggedUserIdentity());
                view.setViewName("redirect:/user-home");
            } else {
                view.addObject("message", this.messages.get("registration.failed"));
                view.setViewName("/user-register");
            }
        }

        return view;
    }

    @RequestMapping(value = {"/user-login"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginUser(final ModelAndView view,
                                  @RequestParam(name = "email", required = false, defaultValue = "") final String email,
                                  @RequestParam(name = "password", required = false, defaultValue = "") final String password) {
        if (email.isEmpty()) {
            this.loggedUser.logoutUser();
            view.setViewName("/user-login");
        } else if (!this.userService.areCredentialsValid(email, password)) {
            view.setViewName("/user-login");
            view.addObject("message", this.messages.get("invalid.credentials"));
        } else {
            this.loggedUser.loginUser(email);

            view.addObject("email", email);
            view.setViewName("redirect:/user-home");
        }

        return view;
    }

    @RequestMapping(value = {"/user-home"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userHome(final ModelAndView view) {
        if (this.loggedUser.isUserLogged()) {
            view.addObject("message", this.messages.get("login.required"));
            view.setViewName("redirect:/user-login");
        } else {
            view.addObject("email", this.loggedUser.getLoggedUserIdentity());
            view.setViewName("/user-home");
        }
        view.getModel().put("my_movies", this.userService.getUserTitles(this.loggedUser.getLoggedUserIdentity()));
        return view;
    }

}
