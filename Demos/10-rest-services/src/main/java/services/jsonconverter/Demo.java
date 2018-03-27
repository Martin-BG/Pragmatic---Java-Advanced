package services.jsonconverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {
        System.out.println(new ObjectMapper()
                .writeValueAsString(
                        new Person("Pesho", 23, true, "Sofia")));
    }
}
