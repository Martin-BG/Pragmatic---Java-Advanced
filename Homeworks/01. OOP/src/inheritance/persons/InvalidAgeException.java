package inheritance.persons;

@SuppressWarnings("serial")
public class InvalidAgeException extends Exception {

    InvalidAgeException(String message) {
        super(message);
    }
}
