package restservices.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorDivideService {

    public double divide(final double a, final double b) {
        return a / b;
    }

}
