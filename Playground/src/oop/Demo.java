package oop;

import oop.citizen.Bulgarian;
import oop.citizen.Citizen;
import oop.citizen.German;
import oop.citizen.Greek;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        List<Citizen> citizens = new ArrayList<Citizen>() {{
            add(new German());
            add(new Bulgarian());
            add(new Greek());
        }};

        citizens.forEach(Citizen::goToWork);
    }
}
