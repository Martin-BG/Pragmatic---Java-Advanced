package bg.mobile.car.controller;

import bg.mobile.car.model.Car;
import bg.mobile.car.service.CarService;
import bg.mobile.config.Messages;
import bg.mobile.user.controller.LoggedUser;
import bg.mobile.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    private final UserService userService;
    private final Messages messages;
    private final LoggedUser loggedUser;

    @Autowired
    public CarController(CarService carService, UserService userService, Messages messages, LoggedUser loggedUser) {
        this.carService = carService;
        this.userService = userService;
        this.messages = messages;
        this.loggedUser = loggedUser;
    }

    @RequestMapping(value = {"/car-search"}, method = RequestMethod.POST, params = "action=search")
    public ModelAndView search(@ModelAttribute Car car, ModelAndView view) {
        List<Car> cars = carService.getCarsByCriteria(car);
        view.getModel().put("cars", cars);
        return view;
    }

    @RequestMapping(value = "/car-search", method = RequestMethod.POST, params = "action=add")
    public ModelAndView add(@ModelAttribute Car car, ModelAndView view) {
        Long userId = userService.getIdByEmail(loggedUser.getLoggedUsername());
        car.setUserId(userId);
        carService.add(car);
        view.setViewName("user-home");
        view.addObject("email", loggedUser.getLoggedUsername());
        view.addObject("message", messages.get("car.added.successfully"));
        return view;
    }

    @RequestMapping(value = {"/car-search"}, method = RequestMethod.POST, params = "action=showMyCars")
    public ModelAndView showMyCars(ModelAndView view) {
        Long userId = userService.getIdByEmail(loggedUser.getLoggedUsername());
        Car car = new Car();
        car.setUserId(userId);
        List<Car> cars = carService.getCarsByCriteria(car);
        view.getModel().put("cars", cars);
        view.getModel().put("car", new Car());
        view.setViewName("user-cars");
        return view;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(name = "id") Long id, ModelAndView view) {
        carService.deleteById(id);
        view.setViewName("user-home");
        view.getModel().put("car", new Car());
        view.addObject("message", messages.get("car.deleted.successfully"));
        return view;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute Car car, ModelAndView view) {
        carService.edit(car);
        view.setViewName("user-home");
        view.addObject("email", loggedUser.getLoggedUsername());
        view.getModel().put("car", new Car());
        view.addObject("message", messages.get("car.edited.successfully"));
        return view;
    }

    @RequestMapping(value = "/car-edit-view", method = RequestMethod.POST)
    public ModelAndView carEditView(@ModelAttribute Car car, ModelAndView view) {
        view.setViewName("car-edit-view");
        view.addObject("email", loggedUser.getLoggedUsername());
        view.getModel().put("car", car);
        return view;
    }
}
