package validation;

import java.util.Objects;

public class RequiredValidation implements Validation{
    @Override
    public boolean validate(Object object) {
        return Objects.nonNull(object);
    }
}
