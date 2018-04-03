package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RequestMapping("/add")
    public double add(@RequestParam(value = "a") double a,
                      @RequestParam(value = "b") double b) {
        return this.calculatorService.add(a, b);
    }

    @RequestMapping("/subtract")
    public double subtract(@RequestParam(value = "a") double a,
                           @RequestParam(value = "b") double b) {
        return this.calculatorService.subtract(a, b);
    }

    @RequestMapping("/multiply")
    public double multiply(@RequestParam(value = "a") double a,
                           @RequestParam(value = "b") double b) {
        return this.calculatorService.multiply(a, b);
    }

    @RequestMapping("/divide")
    public double divide(@RequestParam(value = "a") double a,
                         @RequestParam(value = "b") double b) {
        return this.calculatorService.divide(a, b);
    }
}
