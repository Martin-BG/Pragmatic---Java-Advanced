package restservices.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @RequestMapping("/add")
    public double add(@RequestParam(value = "a") double a,
                      @RequestParam(value = "b") double b) {
        return a + b;
    }

    @RequestMapping("/subtract")
    public double subtract(@RequestParam(value = "a") double a,
                           @RequestParam(value = "b") double b) {
        return a - b;
    }

    @RequestMapping("/multiply")
    public double multiply(@RequestParam(value = "a") double a,
                           @RequestParam(value = "b") double b) {
        return a * b;
    }

    @RequestMapping("/divide")
    public double divide(@RequestParam(value = "a") double a,
                         @RequestParam(value = "b") double b) {
        return a / b;
    }

}
