package lecture2.inheritance.persons;

public enum Sex {

    MALE("Male"),
    FEMALE("Female");

    private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return this.sex;
    }
}
