package oop.citizen;

import oop.contracts.Workable;

public abstract class Citizen implements Workable {

    private String nationality;

    public Citizen(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return this.nationality;
    }

    @Override
    public void goToWork() {
        System.out.println("Super goes to work");
    }
}
