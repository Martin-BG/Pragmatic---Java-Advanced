package restservices.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorMultiplyService {

    public double multiply(final double a, final double b) {
        return a * b;
    }

}
