package demo;

public class Person {
    private String name;
    private int age;
    private boolean isMale;
    private String city;

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
