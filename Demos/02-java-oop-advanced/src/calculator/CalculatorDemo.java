package calculator;

public class CalculatorDemo {
    public static void main(String[] args) {
        System.out.println(Calculator.add(5, 6));
        System.out.println(Calculator.subtract(10,5));
        System.out.println(Calculator.multiply(20, 3));
        System.out.println(Calculator.divide(100,5));

        System.out.println(Calculator.test++);
        System.out.println(Calculator.test);
    }
}
