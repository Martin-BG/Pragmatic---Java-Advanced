package library.parser.api;

public interface ModelParser {

    <S, D> D convert(S source, Class<D> destinationClass);
}
