package Common.exceptions;

public class InvalidCommandArgumentsException extends Exception {

    public String getMessage() {
        return "Command with wrong argument. Type \"help\" to get all commands with their name and description";
    }
}
