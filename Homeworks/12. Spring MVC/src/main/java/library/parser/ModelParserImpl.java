package library.parser;

import library.parser.api.ModelParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelParserImpl implements ModelParser {

    private final ModelMapper mapper;

    @Autowired
    public ModelParserImpl(final ModelMapper mapper) {
        this.mapper = mapper;
        this.initialize();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }

    private void initialize() {
        // Custom mappings belong here
    }
}
