package oop.citizen;

public class Bulgarian extends Citizen {

    public Bulgarian() {
        super("Bulgarian");
    }

    @Override
    public void goToWork() {
        System.out.println(getNationality() + " is working everyday");
    }
}
