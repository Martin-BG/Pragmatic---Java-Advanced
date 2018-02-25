package lecture2.inheritance.persons;

public class Person {

    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 120;
    private String name;
    private int age;
    private Sex sex;

    public Person(String name, int age, Sex sex) throws AgeException {
        this.name = name;
        this.setAge(age);
        this.sex = sex;
    }

    public String getInfo() {
        return this.getBasePersonInfo() + " person";
    }

    String getBasePersonInfo() {
        return this.name + ": " + this.sex + ", " + this.age + " years old";
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) throws AgeException {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new AgeException("Invalid age " + age);
        }

        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public Sex getSex() {
        return this.sex;
    }
}
