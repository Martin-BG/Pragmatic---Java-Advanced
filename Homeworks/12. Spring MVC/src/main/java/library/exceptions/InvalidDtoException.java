package library.exceptions;

public class InvalidDtoException extends Exception {

    private static final long serialVersionUID = 5876844115664006274L;

    public InvalidDtoException(final String message) {
        super(message);
    }
}
