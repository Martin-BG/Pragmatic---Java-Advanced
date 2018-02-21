package person;

public class Car {

    private String model;
    private int maxSpeed;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void driveWithMaximumSpeed(){
        System.out.println("Driving with " + maxSpeed);
    }

}
