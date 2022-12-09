package validation;

public class StringValidation implements Validation{
    @Override
    public boolean validate(Object object) {
        return (object == null) || (object instanceof String);
    }
}
