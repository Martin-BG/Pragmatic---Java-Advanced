package oop.citizen;

public class German extends Citizen {

    public German() {
        super("German");
    }

    @Override
    public void goToWork() {
        System.out.println(getNationality() + " is always on time for work");
    }
}
