package cars;

public class CarDemo {
    final String test = "final should be initialized where declared";

    public static void main(String[] args) {
        Car car = new Car("model");
        car.drive();

        Audi audi = new Audi("audi");
        audi.drive();
        audi.printModel();
    }
}
