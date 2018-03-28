package services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double add(final double a, final double b) {
        return a + b;
    }

    public double subtract(final double a, final double b) {
        return a - b;
    }


    public double multiply(final double a, final double b) {
        return a * b;
    }

    public double divide(final double a, final double b) {
        return a / b;
    }
}
