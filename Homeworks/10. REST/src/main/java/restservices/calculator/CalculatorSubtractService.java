package restservices.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorSubtractService {

    public double subtract(final double a, final double b) {
        return a - b;
    }

}
