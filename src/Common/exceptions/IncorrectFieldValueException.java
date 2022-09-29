package Common.exceptions;

public class IncorrectFieldValueException extends Exception{
    public IncorrectFieldValueException(String field, String value, String correct){
        super("Incorrect value for field " + field + " this field can't be \"" + value + "\". Value must be " + correct);
    }
}
