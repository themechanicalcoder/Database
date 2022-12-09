package validation;

public class IntValidation implements Validation{
    public boolean validate(Object object){
        return (object == null) || (object instanceof  Integer);
    }
}
