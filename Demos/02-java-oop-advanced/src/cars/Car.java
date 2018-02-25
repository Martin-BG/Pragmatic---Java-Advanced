package cars;

public class Car {

    private String model;

    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    void drive(){
        System.out.println("Driving with 30 kilometers per hour");
    }

}
