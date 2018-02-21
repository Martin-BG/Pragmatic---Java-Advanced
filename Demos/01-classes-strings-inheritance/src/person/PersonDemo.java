package person;

public class PersonDemo {
    public static void main(String[] args) {
        Person tosho;
        Car bmw = new Car();
        bmw.setModel("BMW");
        bmw.setMaxSpeed(220);
        tosho = new Person();
        tosho.setAge(15);
        tosho.setName("Tosho");
        System.out.println(tosho.getName());
        System.out.println(tosho.getAge());
        tosho.run();
        tosho.eat();

        Car audi = new Car();
        audi.setModel("Audi");
        audi.setMaxSpeed(200);
        Person gosho = new Person("Georgi", 28, audi);
        gosho.eat();
        gosho.run();
        gosho.getCar().driveWithMaximumSpeed();
    }
}
