package zoo;

import java.util.ArrayList;
import java.util.List;

public class AnimalDemo {

    public static void main(String[] args) {
//        Animal animal = new Animal();
        Lion lion = new Lion();
        lion.makeSomeNoise();
        lion.run();

        Animal cat = new Lion();
        cat.makeSomeNoise();

        List<Animal> animals = new ArrayList<>();
        animals.add(lion);
        animals.add(cat);

    }
}
