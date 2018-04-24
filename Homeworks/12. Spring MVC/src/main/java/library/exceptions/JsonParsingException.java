package library.exceptions;

public class JsonParsingException extends Exception {

    private static final long serialVersionUID = 7415235129141219613L;

    public JsonParsingException(final String message, final Throwable e) {
        super(message, e);
    }
}
