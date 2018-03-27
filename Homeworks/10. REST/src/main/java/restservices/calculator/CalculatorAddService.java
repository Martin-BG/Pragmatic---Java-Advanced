package restservices.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorAddService {

    public double add(final double a, final double b) {
        return a + b;
    }

}
