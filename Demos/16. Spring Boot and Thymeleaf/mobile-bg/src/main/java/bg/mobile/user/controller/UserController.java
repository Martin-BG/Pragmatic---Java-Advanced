package bg.mobile.user.controller;

import bg.mobile.car.model.Car;
import bg.mobile.config.Messages;
import bg.mobile.user.model.User;
import bg.mobile.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public UserController(UserService userService, LoggedUser loggedUser, Messages messages) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.messages = messages;
    }

    @RequestMapping(value = {"/user-register"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerUser(ModelAndView view,
                                     @RequestParam(name = "email", defaultValue = "") String email,
                                     @RequestParam(name = "password", defaultValue = "") String password) {
        if (email.isEmpty() || password.isEmpty()) {
            view.setViewName("/user-register");
        } else {
            boolean registered = false;
            try {
                User user = new User(email, password);
                registered = userService.add(user);
            } catch (DataAccessException e) {
                view.addObject("message", e);
            }
            if (registered) {
                loggedUser.loginUser(email);
                view.addObject("name", loggedUser.getLoggedUsername());
                view.setViewName("redirect:/user-home");
            } else {
                view.setViewName("/user-register");
            }
        }

        return view;
    }

    @RequestMapping(value = {"/", "/user-login"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginUser(ModelAndView view,
                                  @RequestParam(name = "email", required = false, defaultValue = "") String email,
                                  @RequestParam(name = "password", required = false, defaultValue = "") String password) {
        if (email.isEmpty()) {
            loggedUser.logoutUser();
            view.setViewName("/user-login");
        } else if (!userService.areCredentialsValid(email, password)) {
            view.setViewName("/user-login");
            view.addObject("message", messages.get("invalid.credentials"));
        } else {
            loggedUser.loginUser(email);

            view.addObject("email", email);
            view.setViewName("redirect:/user-home");
        }

        return view;
    }

    @RequestMapping(value = {"/user-home"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userHome(ModelAndView view) {
        if (loggedUser.isUserLogged()) {
            view.addObject("message", messages.get("login.required"));
            view.setViewName("redirect:/user-login");
        } else {
            view.addObject("email", loggedUser.getLoggedUsername());
            view.setViewName("/user-home");
        }
        view.getModel().put("car", new Car());
        return view;
    }

}
