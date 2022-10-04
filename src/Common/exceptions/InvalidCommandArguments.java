package Common.exceptions;

public class InvalidCommandArguments extends Exception {
    private String argument = "";

    public InvalidCommandArguments(){}

    public InvalidCommandArguments(String argument){
        this.argument = argument;
    }
    public String getMessage() {
        return "Command with wrong argument: " + argument + ". Type \"help\" to get all commands with their name and description";
    }
}
