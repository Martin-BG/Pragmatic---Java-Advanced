package library.parser.api;

import library.exceptions.JsonParsingException;

public interface JsonParser {

    <T> T read(Class<T> objectClass, String fileContent) throws JsonParsingException;

    <T> String write(T object) throws JsonParsingException;
}
