package library.parser.api;

import library.exceptions.InvalidDtoException;

public interface DtoValidator {

    <T> boolean isValid(T t) throws InvalidDtoException;
}
