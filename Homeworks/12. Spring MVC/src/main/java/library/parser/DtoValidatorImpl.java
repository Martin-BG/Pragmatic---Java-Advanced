package library.parser;

import library.exceptions.InvalidDtoException;
import library.parser.api.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public final class DtoValidatorImpl implements DtoValidator {

    private final Validator validator;

    @Autowired
    public DtoValidatorImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> boolean isValid(T object) throws InvalidDtoException {
        if (object == null) {
            throw new InvalidDtoException(null);
        }

        final Set<ConstraintViolation<T>> constraints = this.validator.validate(object);

        if (constraints.isEmpty()) {
            return true;
        }

        final StringBuilder sb = new StringBuilder();
        constraints.forEach(c -> sb.append(c.getMessage()).append(System.lineSeparator()));
        throw new InvalidDtoException(sb.toString().trim());
    }
}
