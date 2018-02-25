package cars;

public class Audi extends Car {

    public Audi(String model) {
        super(model);
    }

    @Override
    void drive() {
        super.drive();
        System.out.println("Driving with 200 kilometers per hour");
    }

    void printModel(){
        System.out.println(super.getModel());
    }

}
