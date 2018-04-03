package helpers;

public class Person {
    private final String name;
    private final int age;
    private final boolean isMale;
    private final String city;

    public Person(final String name, final int age, final boolean isMale, final String city) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isMale() {
        return this.isMale;
    }

    public String getCity() {
        return this.city;
    }
}
