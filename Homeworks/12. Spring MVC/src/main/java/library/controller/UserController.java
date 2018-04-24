package library.controller;

import library.config.Messages;
import library.exceptions.InvalidDtoException;
import library.exceptions.JsonParsingException;
import library.model.dto.binding.UserRegistrationDto;
import library.model.dto.view.UserViewDto;
import library.parser.api.DtoValidator;
import library.parser.api.JsonParser;
import library.persistance.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public final class UserController {

    private final Messages messages;
    private final DtoValidator validator;
    private final JsonParser jsonParser;
    private final SimpleLoggedUserRegister loggedRegister;
    private final UserService userService;

    @Autowired
    public UserController(final Messages messages,
                          final DtoValidator validator,
                          final JsonParser jsonParser,
                          final SimpleLoggedUserRegister loggedRegister,
                          final UserService userService) {
        this.messages = messages;
        this.validator = validator;
        this.jsonParser = jsonParser;
        this.loggedRegister = loggedRegister;
        this.userService = userService;
    }

    @RequestMapping(value = {"/user-register"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerUser(final ModelAndView view,
                                     @RequestParam(name = "user", defaultValue = "") final String userName,
                                     @RequestParam(name = "email", defaultValue = "") final String email,
                                     @RequestParam(name = "age", required = false, defaultValue = "0") final Integer age) {
        if (userName.isEmpty() && email.isEmpty()) {
            view.setViewName("/user-register");
        } else {
            boolean registered = false;

            try {
                UserRegistrationDto dto = new UserRegistrationDto(userName, email, age);
                registered = this.validator.isValid(dto) && this.userService.registerUser(dto);
            } catch (InvalidDtoException e) {
                view.addObject("message", e.getMessage());
            }

            if (registered) {
                this.loggedRegister.loginUser(userName);
                view.addObject("name", this.loggedRegister.getLoggedUserName());
                view.setViewName("redirect:/user-home");
            } else {
                view.setViewName("/user-register");
            }
        }

        return view;
    }

    @RequestMapping(value = {"/", "/user-login"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginUser(final ModelAndView view,
                                  @RequestParam(name = "user", required = false, defaultValue = "") final String userName) {
        if (userName.isEmpty()) {
            this.loggedRegister.logoutUser();

            view.setViewName("/user-login");
        } else if (!this.userService.isValid(userName)) {
            view.setViewName("/user-login");
            view.addObject("message", this.messages.get("invalid.credentials"));
        } else {
            this.loggedRegister.loginUser(userName);

            view.addObject("name", userName);
            view.setViewName("redirect:/user-home");
        }

        return view;
    }

    @RequestMapping(value = {"/user-home"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userHome(final ModelAndView view) {
        if (this.loggedRegister.noLoggedUser()) {
            view.addObject("message", this.messages.get("login.required"));
            view.setViewName("redirect:/user-login");
        } else {
            view.addObject("name", loggedRegister.getLoggedUserName());
            view.setViewName("/user-home");
        }
        return view;
    }


    @RequestMapping(value = {"/users"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView viewAllUsers(final ModelAndView view) {
        if (this.loggedRegister.noLoggedUser()) {
            view.addObject("message", this.messages.get("login.required"));
            view.setViewName("redirect:/user-login");
        } else {
            final List<UserViewDto> books = this.userService.getAllUsers();
            try {
                view.addObject("message", this.jsonParser.write(books));
            } catch (JsonParsingException e) {
                view.addObject("message", e.getMessage());
            }

            view.setViewName("/users");
        }
        return view;
    }
}
