package lecture2.inheritance.persons;

public class Student extends Person {

    private double score;

    public Student(String name, int age, Sex sex, double score) throws InvalidAgeException {
        super(name, age, sex);
        this.score = score;
    }

    @Override
    public String getInfo() {
        return super.getBasePersonInfo() + " student. Score: " + this.score;
    }
}
