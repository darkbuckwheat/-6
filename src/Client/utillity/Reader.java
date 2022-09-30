package Client.utillity;

import Client.exceptions.IllegalValueException;
import Client.exceptions.InvalidInputException;
import Common.exceptions.IncorrectFieldValueException;

public interface Reader<T> {
    T read() throws IllegalValueException, IllegalArgumentException, InvalidInputException, IncorrectFieldValueException;
}
