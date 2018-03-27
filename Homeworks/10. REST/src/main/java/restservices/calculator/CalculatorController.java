package restservices.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorAddService addService;
    private final CalculatorSubtractService subtractService;
    private final CalculatorMultiplyService multiplyService;
    private final CalculatorDivideService divideService;

    @Autowired
    public CalculatorController(CalculatorAddService addService,
                                CalculatorSubtractService subtractService,
                                CalculatorMultiplyService multiplyService,
                                CalculatorDivideService divideService) {
        this.addService = addService;
        this.subtractService = subtractService;
        this.multiplyService = multiplyService;
        this.divideService = divideService;
    }

    @RequestMapping("/add")
    public double add(@RequestParam(value = "a") double a,
                      @RequestParam(value = "b") double b) {
        return this.addService.add(a, b);
    }

    @RequestMapping("/subtract")
    public double subtract(@RequestParam(value = "a") double a,
                           @RequestParam(value = "b") double b) {
        return this.subtractService.subtract(a, b);
    }

    @RequestMapping("/multiply")
    public double multiply(@RequestParam(value = "a") double a,
                           @RequestParam(value = "b") double b) {
        return this.multiplyService.multiply(a, b);
    }

    @RequestMapping("/divide")
    public double divide(@RequestParam(value = "a") double a,
                         @RequestParam(value = "b") double b) {
        return this.divideService.divide(a, b);
    }
}
