package demo;

public class Person {
    private final String name;
    private final int age;
    private final boolean isMale;
    private final String city;

    public Person(String name, int age, boolean isMale, String city) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getCity() {
        return city;
    }
}
