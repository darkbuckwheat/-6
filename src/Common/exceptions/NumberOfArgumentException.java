package Common.exceptions;

public class NumberOfArgumentException extends Exception{
    private String message;

    public NumberOfArgumentException(String command, Integer right, Integer provided) {
        message = "Command " + command + "  takes " + right + " arguments. " + provided + " given.";
    }

    public String getMessage(){return message;}
}
