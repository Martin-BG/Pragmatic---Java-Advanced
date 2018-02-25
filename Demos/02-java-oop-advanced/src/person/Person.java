package person;

public class Person {
    private String name;
    private int age;

     String getName() {
        return name;
    }
     void setName(String name) {
        this.name = name;
    }

     int getAge() {
        return age;
    }

     void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person with name: " + name + " and age: " + age;
    }
}
