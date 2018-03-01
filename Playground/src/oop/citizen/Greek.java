package oop.citizen;

public class Greek extends Citizen {

    public Greek() {
        super("Greek");
    }

    @Override
    public void goToWork() {
        System.out.println(getNationality() + " works too");
    }
}
