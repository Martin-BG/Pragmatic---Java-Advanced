package lecture2.inheritance;

import lecture2.inheritance.persons.*;

import java.util.ArrayList;
import java.util.List;

public class PersonsDemo {

    public static void main(String[] args) throws InvalidAgeException {

        List<Person> persons = new ArrayList<Person>() {
            {
                try {
                    add(new Employee("Invalid Age 1", 134, Sex.MALE, 100));
                } catch (InvalidAgeException ae) {
                    ae.printStackTrace();
                }

                try {
                    add(new Employee("Invalid Age 2", -3, Sex.MALE, 100));
                } catch (InvalidAgeException ae) {
                    ae.printStackTrace();
                }

                add(new Person("Person 1", 18, Sex.FEMALE));
                add(new Person("Person 2", 33, Sex.MALE));
                add(new Student("Student 1", 15, Sex.FEMALE, 4.87));
                add(new Student("Student 2", 13, Sex.MALE, 5.13));
                add(new Employee("Employee 1", 17, Sex.MALE, 43.5));
                add(new Employee("Employee 2", 34, Sex.MALE, 100d));
            }
        };

        persons.forEach(person -> System.out.println(person.getInfo()));

        persons.stream()
                .filter(person -> person instanceof Employee)
                .map(person -> (Employee) person)
                .forEach(employee -> System.out.printf(
                        "Payment for 2 hours overtime for %s: %.2f%n",
                        employee.getName(),
                        employee.calculateOvertime(2d)));
    }
}
