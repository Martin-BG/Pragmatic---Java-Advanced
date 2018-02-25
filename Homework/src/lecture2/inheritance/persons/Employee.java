package lecture2.inheritance.persons;

public class Employee extends Person {

    private static final double OVERTIME_SALARY_MODIFIER = 1.5;
    private static final double WORK_HOURS_PER_DAY = 8d;
    private static final int MIN_WORK_AGE = 18;

    private double daySalary;

    public Employee(String name, int age, Sex sex, double daySalary) throws InvalidAgeException {
        super(name, age, sex);
        this.daySalary = daySalary;
    }

    public double calculateOvertime(double hours) {
        return (this.getAge() >= MIN_WORK_AGE) ?
                this.daySalary / WORK_HOURS_PER_DAY * hours * OVERTIME_SALARY_MODIFIER :
                0d;
    }

    @Override
    public String getInfo() {
        return super.getBasePersonInfo() + " employee. Daily Salary : " + this.daySalary;
    }
}
